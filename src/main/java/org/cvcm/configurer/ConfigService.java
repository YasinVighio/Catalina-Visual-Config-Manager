package org.cvcm.configurer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import org.cvcm.dto.ServiceResponse;
import org.cvcm.models.Server;
import org.cvcm.dto.ConfigurationDTO;
import org.cvcm.utils.AppConfigs;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ConfigService {

    private final static Logger LOGGER = AppConfigs.LOGGER;

    List<String> tomcatDirPrefixes = AppConfigs.getTomcatDirPrefixes();

    public ServiceResponse getTomcatInstallDirs() {
        ServiceResponse response = new ServiceResponse();
        try {
            List<String> installDirs = new ArrayList<>();

            //get all root dirs
            File[] roots = File.listRoots();

            //traverse all dirs
            Arrays.stream(roots).parallel().forEach(root -> findTomcatDirectories(root, installDirs));

            //default alias is number
            Map<String, String> dirsWithAlias = IntStream.range(0, installDirs.size())
                    .boxed()
                    .collect(Collectors.toMap(
                            installDirs::get,                    // Key: installation directory
                            i -> String.valueOf(i + 1)               // Value: number (alias)
                    ));

            response.setMessage("Fetched and cached directories successfully");
            String cacheMsg = cacheFetchedInstallDirs(dirsWithAlias).getMessage();
            response.setDataMap(dirsWithAlias);
            response.setMessage(response.getMessage() + " and "+cacheMsg);
        } catch (Exception e) {
            LOGGER.severe("ConfigService.getTomcatInstallDirs: "+e.getMessage());
            response.setMessage("Failed to get installation directories, check logs");
        }
        return response;
    }

    public ServiceResponse getSavedTomcatInstallDirs() {
        ServiceResponse response = new ServiceResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, String> installDirs;
        try {
            installDirs = objectMapper
                    .readValue(
                            new File(AppConfigs.getSavedInstallationDirFileName()),
                            new TypeReference<>() {
                            });
            response.setMessage("Fetched cached directories successfully");
            response.setDataMap(installDirs);
        } catch (Exception e) {
            LOGGER.severe("ConfigService.getSavedTomcatInstallDirs: "+e.getMessage());
            response.setMessage("Error fetching cached directories, check logs");
        }
        return response;
    }

    private void findTomcatDirectories(File directory, List<String> installDirs) {
        try {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) { //check if directory and name begins with given prefix
                        if (isValidTomcatDir(file)) {
                            installDirs.add(file.getAbsolutePath());
                        } else {
                            findTomcatDirectories(file, installDirs);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.severe("ConfigService.findTomcatDirectories: "+e.getMessage());
            throw e;
        }
    }

    private boolean isValidTomcatDir(File file){
        boolean startsWithTomcatDirPrefix = this.tomcatDirPrefixes.stream()
                    .anyMatch(prefix -> file.getName().toLowerCase().startsWith(prefix.toLowerCase()));

        if(startsWithTomcatDirPrefix) {
            File tomcatBatchFile = new File(file.getAbsoluteFile() + "\\bin\\catalina.bat");
            return tomcatBatchFile.exists();
        }
        return false;
    }

    public ServiceResponse cacheFetchedInstallDirs(Map<String, String> installDirs) {
        ServiceResponse response = new ServiceResponse();
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            File cachedDirFile = new File(AppConfigs.getSavedInstallationDirFileName());

            if(cachedDirFile.exists()) {
                //do not overwrite aliases
                Map<String, String> preCachedDirs = objectMapper
                        .readValue(
                                cachedDirFile,
                                new TypeReference<>() {
                                });


                for (String savedTomcatDir : installDirs.keySet()) {
                    if (preCachedDirs.containsKey(savedTomcatDir)) {
                        installDirs.put(savedTomcatDir, preCachedDirs.get(savedTomcatDir));
                    }
                }
            }
            objectMapper.writeValue(new File(AppConfigs.getSavedInstallationDirFileName()), installDirs);
            response.setMessage("Successfully cached directories with aliases");
        }
        catch (Exception e){
            LOGGER.severe("ConfigService.cacheInstallDirs: "+e.getMessage());
            response.setMessage("Directories not cached, check logs");
        }
        return response;
    }


    public ServiceResponse updateAlias(String tomcatDir, String alias) {
        ServiceResponse response = new ServiceResponse();
        try {
            ObjectMapper objectMapper = new ObjectMapper();

            Map<String, String> preCachedDirs = objectMapper
                    .readValue(
                            new File(AppConfigs.getSavedInstallationDirFileName()),
                            new TypeReference<>() {
                            });

            preCachedDirs.put(tomcatDir, alias);

            objectMapper.writeValue(new File(AppConfigs.getSavedInstallationDirFileName()), preCachedDirs);
            response.setMessage("Successfully updated alias");
        }
        catch (Exception e){
            LOGGER.severe("ConfigService.updateAlias: "+e.getMessage());
            response.setMessage("Alias not updated, check logs");
        }
        return response;
    }


    public ServiceResponse getConfigurations(String tomcatPath) {
        ServiceResponse response = new ServiceResponse();
        try {
            Server serverConfig = readServerXML(tomcatPath);
            if (serverConfig != null) {
                response.setConfiguration(fillConfigDTO(serverConfig));
                response.setMessage("Configurations fetched successfully");
            } else {
                response.setMessage("Configurations can not be fetched");
            }
        } catch (Exception e) {
            LOGGER.severe("ConfigService.getConfigurations: "+e.getMessage());
            response.setMessage("Failed to get configurations, check logs");
        }
        return response;
    }

    public static Server readServerXML(String tomcatDir) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Server.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File xmlFile = new File(getTomcatServerFilePath(tomcatDir, false));
        return (Server) unmarshaller.unmarshal(xmlFile);
    }

    private ConfigurationDTO fillConfigDTO(Server serverConfigDTO) throws Exception {
        ConfigurationDTO configDTO = new ConfigurationDTO();
        configDTO.setAccessLogPattern(serverConfigDTO.getService().getEngine().getHost().getValve().getPattern());
        configDTO.setConnectorPort(serverConfigDTO.getService().getConnectors()[0].getPort());
        configDTO.setConnectionTimeout(serverConfigDTO.getService().getConnectors()[0].getConnectionTimeout());
        return configDTO;
    }

    public ServiceResponse setCatalinaHome(String tomcatDir) {
        ServiceResponse response = new ServiceResponse();
        try {
            String command = "powershell.exe -Command setx CATALINA_HOME '" + tomcatDir + "'";
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd", "/c", command);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                response.setMessage("Environment variable set successfully");
            } else {
                response.setMessage("Command failed to be executed");
            }

        } catch (Exception e) {
            LOGGER.severe("ConfigService.setCatalinaHome: "+e.getMessage());
            response.setMessage("Can not set CATALINA_HOME, check logs");
        }
        return response;
    }

    public ServiceResponse runSelectedTomcat(String tomcatDir, String jpdaPort, boolean isDebugMode) {
        ServiceResponse response = new ServiceResponse();
        try {
            String command = "";
            if (isDebugMode) {
                command += " set CATALINA_OPTS=-agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=" + jpdaPort + " && ";
            }

            command += "\"" + tomcatDir + "\\bin\\catalina.bat\" ";

            if (isDebugMode) {
                command += "start jpda";
            } else {
                command += "start";
            }

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd", "/c", command);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                response.setMessage("Run command successfully executed");
            } else {
                response.setMessage("Command failed to be executed");
            }
        } catch (Exception e) {
            LOGGER.severe("ConfigService.runSelectedTomcat: "+e.getMessage());
            response.setMessage("Tomcat failed to be executed, check logs");
        }
        return response;
    }

    public ServiceResponse stopSelectedTomcat(String tomcatDir) {
        ServiceResponse response = new ServiceResponse();
        try {
            String command = tomcatDir + "\\bin\\catalina.bat stop";

            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("cmd", "/c", command);
            Process process = processBuilder.start();
            int exitCode = process.waitFor();

            if (exitCode == 0) {
                response.setMessage("Stop command successfully executed");
            } else {
                response.setMessage("Command failed to be executed");
            }
        } catch (Exception e) {
            LOGGER.severe("ConfigService.stopSelectedTomcat: "+e.getMessage());
            response.setMessage("Tomcat failed to be stopped, check logs");
        }
        return response;
    }

    public ServiceResponse createBackupOfServerXML(String tomcatDir) {
        ServiceResponse response = new ServiceResponse();
        try {
            String serverXMLFilePath = getTomcatServerFilePath(tomcatDir, false);
            String serverBackupXMLFilePath = getTomcatServerFilePath(tomcatDir, true);
            File sourceFile = new File(serverXMLFilePath);
            File destinationFile = new File(serverBackupXMLFilePath);

            if (destinationFile.exists()) {
                response.setMessage("Backup file already exists at: " + destinationFile.getAbsolutePath());
            } else {
                FileInputStream inputStream = new FileInputStream(sourceFile);
                FileOutputStream outputStream = new FileOutputStream(destinationFile);

                byte[] buffer = new byte[1024];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                inputStream.close();
                outputStream.close();
                response.setMessage("Backup successful: "+serverBackupXMLFilePath);
            }
        } catch (Exception e) {
            LOGGER.severe("ConfigService.createBackupOfServerXML: "+e.getMessage());
            response.setMessage("Error in backup process, check logs");
        }
        return response;
    }

    public ServiceResponse restoreBackupOfServerXML(String tomcatDir) {
        ServiceResponse response = new ServiceResponse();
        try {
            String serverXMLFilePath = getTomcatServerFilePath(tomcatDir, false);
            String serverBackupXMLFilePath = getTomcatServerFilePath(tomcatDir, true);
            File backupFile = new File(serverBackupXMLFilePath);
            File serverFile = new File(serverXMLFilePath);

            if (!backupFile.exists()) {
                response.setMessage("Backup file does not exists");
            } else {
                FileInputStream inputStream = new FileInputStream(backupFile);
                FileOutputStream outputStream = new FileOutputStream(serverFile);

                byte[] buffer = new byte[1024];
                int length;

                while ((length = inputStream.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, length);
                }
                inputStream.close();
                outputStream.close();
                response.setMessage("Backup successful");
            }
        } catch (Exception e) {
            LOGGER.severe("ConfigService.restoreBackupOfServerXML: "+e.getMessage());
            response.setMessage("Error in restoring backup, check logs");
        }
        return response;
    }

    public ServiceResponse saveChangesInServerXML(String tomcatDir, ConfigurationDTO newConfig) {
        ServiceResponse response = new ServiceResponse();
        try{
            //first backup
            response.setMessage(createBackupOfServerXML(tomcatDir).getMessage());

            String serverBackupXMLFilePath = getTomcatServerFilePath(tomcatDir, true);
            File backupFile = new File(serverBackupXMLFilePath);

            Server serverConfig = readServerXML(tomcatDir);

            if(serverConfig!=null && backupFile.exists()) {
                if (newConfig.getConnectorPort() != null) {
                    serverConfig.getService().getConnectors()[0].setPort(newConfig.getConnectorPort());
                }
                if (newConfig.getConnectionTimeout() != null) {
                    serverConfig.getService().getConnectors()[0].setConnectionTimeout(newConfig.getConnectionTimeout());
                }
                if (newConfig.getAccessLogPattern() != null) {
                    serverConfig.getService().getEngine().getHost().getValve().setPattern(newConfig.getAccessLogPattern());
                }
                JAXBContext jaxbContext = JAXBContext.newInstance(Server.class);
                File xmlFile = new File(getTomcatServerFilePath(tomcatDir, false));
                Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
                jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
                jaxbMarshaller.setProperty(Marshaller.JAXB_FRAGMENT, true); // To not write XML declaration
                jaxbMarshaller.marshal(serverConfig, xmlFile);
                response.setMessage(response.getMessage() + " Changes done successfully");
            }
            else {
                response.setMessage(response.getMessage() + " No changes done");
            }
        }
        catch (Exception e){
            LOGGER.severe("ConfigService.saveChangesInServerXML: "+e.getMessage());
            response.setMessage("Changes not done successfully, check logs");
        }
        return response;
    }

    private static String getTomcatServerFilePath(String tomcatDir, boolean isBackupFile){
        StringBuilder sb = new StringBuilder(tomcatDir);
        sb.append(AppConfigs.getTomcatConfigDirName());
        if(isBackupFile){
            sb.append(AppConfigs.getTomcatServerFileBackupName());
        }
        else {
            sb.append(AppConfigs.getTomcatServerFileName());
        }
       return sb.toString();
    }
}
