package org.cvcm;

import org.cvcm.configurer.ConfigService;
import org.cvcm.screens.MainWindow;
import org.cvcm.utils.AppConfigs;


import javax.swing.WindowConstants;

/**
 *
 * @author Yasin
 */
public class VisualCatalinaConfigurer {

    public static void main(String[] args) {
        initializeApp();
    }

    public static void initializeApp(){
        ConfigService configService = new ConfigService();
        //dependency inj
        MainWindow mainWindow = new MainWindow(configService);

        mainWindow.setTitle(AppConfigs.getAppName());
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
