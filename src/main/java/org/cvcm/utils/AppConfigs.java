package org.cvcm.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.stream.Collectors;

public class AppConfigs {

    public static final Logger LOGGER = Logger.getLogger("CVCMLogger");
    public static FileHandler logFileHandler;
    public static Properties systemProps = null;

    static {
        try {
            loadSystemProps();
            configureLogger();
        }
        catch (Exception e){
            throw new RuntimeException("Application failed to start");
        }
    }

    public static void loadSystemProps() throws Exception{
        systemProps = new Properties();

        /**
         * System will load app.props from resources if other file not found
         * It will overwrite if any of the property is not valid in file outside resources
         */

        File propFile = new File("application.properties");
        if(propFile.exists()){
            InputStream configs = new FileInputStream("application.properties");
            systemProps.load(configs);
            if(!validateProperties()){
                configs.close();
                getConfigsFromResource();
            }
        } else {
            getConfigsFromResource();
        }
    }

    private static void getConfigsFromResource() throws Exception {
        File propFile = new File("application.properties");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream configs = loader.getResourceAsStream("application.properties");
        systemProps.load(configs);
        configs.close();
        InputStream appProps = loader.getResourceAsStream("application.properties");
        Files.deleteIfExists(propFile.toPath());
        Files.copy(appProps, propFile.toPath());
        appProps.close();
    }

    private static void configureLogger() throws Exception {
        logFileHandler = new FileHandler(systemProps.getProperty(Constants.LOGGER_FILE));
        LOGGER.addHandler(logFileHandler);
        SimpleFormatter formatter = new SimpleFormatter();
        logFileHandler.setFormatter(formatter);
        LOGGER.info("Logger configured successfully");
    }

    private static boolean validateProperties(){
        return getAppName()!=null && getTomcatConfigDirName()!=null && getTomcatServerFileName()!=null
                && getSavedInstallationDirFileName()!=null && getTomcatServerFileBackupName()!=null && getTomcatDirPrefixes() !=null;
    }


    public static String getAppName() {
        return systemProps.getProperty(Constants.APP_NAME);
    }

    public static String getTomcatServerFileName() {
        return systemProps.getProperty(Constants.TOMCAT_SERVER_FILE_NAME);
    }

    public static String getTomcatServerFileBackupName() {
        return systemProps.getProperty(Constants.TOMCAT_SERVER_FILE_BACKUP_NAME);
    }

    public static String getSavedInstallationDirFileName() {
        return systemProps.getProperty(Constants.TOMCAT_INSTALLATION_DIRS_JSON);
    }

    public static String getTomcatConfigDirName() {
        return systemProps.getProperty(Constants.TOMCAT_CONFIG_DIR);
    }


    public static List<String> getTomcatDirPrefixes() {
        String prefixes = systemProps.getProperty(Constants.TOMCAT_DIR_PREFIXES);
        if (prefixes != null && !prefixes.isEmpty()) {
            return Arrays.stream(prefixes.split(","))
                    .map(String::trim)
                    .collect(Collectors.toList());
        }
        return null;
    }

}
