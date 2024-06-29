package vcc;

import vcc.configurer.ConfigService;
import vcc.screens.MainWindow;

import javax.swing.*;

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

        mainWindow.setTitle("Catalina Config Manager");
        mainWindow.setResizable(false);
        mainWindow.setVisible(true);
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
