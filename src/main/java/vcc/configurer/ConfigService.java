package vcc.configurer;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import vcc.dto.ConfigurationDTO;
import vcc.dto.ServiceResponse;
import vcc.models.Server;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ConfigService {
    public ServiceResponse getTomcatInstallDirs() {
        ServiceResponse response = new ServiceResponse();
        try {
            List<String> installDirs = new ArrayList<>();

            //get all root dirs
            File[] roots = File.listRoots();

            //traverse all dirs
            Arrays.stream(roots).parallel().forEach(root -> findTomcatDirectories(root, installDirs));

            cacheInstallDirs(installDirs);
            response.setDataList(installDirs);
            response.setMessage("Fetched and cached directories successfully");
        } catch (Exception e) {
            e.printStackTrace();
            response.setMessage("Failed to get installation directories: " + e.getMessage());
        }
        return response;
    }

    public ServiceResponse getSavedTomcatInstallDirs() {
        ServiceResponse response = new ServiceResponse();
        ObjectMapper objectMapper = new ObjectMapper();
        List<String> installDirs = null;
        try {
            installDirs = objectMapper.readValue(new File("installDirs.json"), new TypeReference<List<String>>() {
            });
            response.setMessage("Fetched cached directories successfully");
            response.setDataList(installDirs);
        } catch (Exception e) {
            response.setMessage("Error fetching cached directories: " + e.getMessage());
        }
        return response;
    }

    private void findTomcatDirectories(File directory, List<String> installDirs) {
        try {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    //if directory matches add else traverse nested dirs
                    if (file.isDirectory() && file.getName().startsWith("apache-tomcat-")) {
                        installDirs.add(file.getAbsolutePath());
                    } else if (file.isDirectory()) {
                        findTomcatDirectories(file, installDirs);
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void cacheInstallDirs(List<String> installDirs) throws Exception {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new File("installDirs.json"), installDirs);
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
            response.setMessage("Failed to get configurations: " + e.getMessage());
        }
        return response;
    }

    public static Server readServerXML(String tomcatPath) throws Exception {
        JAXBContext jaxbContext = JAXBContext.newInstance(Server.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        File xmlFile = new File(tomcatPath + "\\conf\\server.xml");
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
            e.printStackTrace();
            response.setMessage("Process failed: " + e.getMessage());
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
            response.setMessage("Tomcat failed to be executed");
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
            response.setMessage("Tomcat failed to be stopped");
        }
        return response;
    }

    public ServiceResponse createBackupOfServerXML(String tomcatDir) {
        ServiceResponse response = new ServiceResponse();
        try {
            String serverXMLFilePath = tomcatDir + "\\conf\\server.xml";
            String serverBackupXMLFilePath = tomcatDir + "\\conf\\bkp_server.xml";
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
                response.setMessage("Backup successful (Find bkp_server.xml) inside tomcat conf folder");
            }
        } catch (Exception e) {
           response.setMessage("Error in backup process: "+e.getMessage());
        }
        return response;
    }

    public ServiceResponse restoreBackupOfServerXML(String tomcatDir) {
        ServiceResponse response = new ServiceResponse();
        try {
            String serverXMLFilePath = tomcatDir + "\\conf\\server.xml";
            String serverBackupXMLFilePath = tomcatDir + "\\conf\\bkp_server.xml";
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
            response.setMessage("Error in restoring backup: "+e.getMessage());
        }
        return response;
    }

    public ServiceResponse saveChangesInServerXML(String tomcatDir, ConfigurationDTO newConfig) {
        ServiceResponse response = new ServiceResponse();
        try{
            //first backup
            response.setMessage(createBackupOfServerXML(tomcatDir).getMessage());

            String serverBackupXMLFilePath = tomcatDir + "\\conf\\bkp_server.xml";
            File backupFile = new File(serverBackupXMLFilePath);

            Server serverConfig = readServerXML(tomcatDir);

            if(serverConfig!=null && backupFile.exists()) {
                if (newConfig.getConnectorPort() != null) {
                    serverConfig.getService().getConnectors()[0].setPort(newConfig.getConnectorPort());
                }
                if (newConfig.getConnectionTimeout() != null) {
                    serverConfig.getService().getConnectors()[0].setConnectionTimeout(newConfig.getConnectorPort());
                }
                if (newConfig.getAccessLogPattern() != null) {
                    serverConfig.getService().getEngine().getHost().getValve().setPattern(newConfig.getAccessLogPattern());
                }
                JAXBContext jaxbContext = JAXBContext.newInstance(Server.class);
                File xmlFile = new File(tomcatDir + "\\conf\\server.xml");
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
            response.setMessage("Changes not done successfully: "+e.getMessage());
        }
        return response;
    }
}
