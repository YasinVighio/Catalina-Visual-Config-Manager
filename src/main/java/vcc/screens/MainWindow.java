/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vcc.screens;

import vcc.configurer.ConfigService;
import vcc.dto.ConfigurationDTO;
import vcc.dto.ServiceResponse;
import vcc.utils.StringUtils;

import javax.swing.*;
import java.util.List;

/**
 *
 * @author Yasin
 */

public class MainWindow extends JFrame {

    private ConfigService configServiceClient;

    public MainWindow(ConfigService configService){
        this.configServiceClient = configService;
        this.initComponents();
    }

    /**
     * Creates new form MainWindow
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new JMenuItem();
        jMenuItem2 = new JMenuItem();
        connectionTimeOutLbl = new JLabel();
        catalinaHomeLabel = new JLabel();
        connectorPortInput = new JTextField();
        connectorPortLbl1 = new JLabel();
        connectionTimeOutInput = new JTextField();
        accessLogPtrnLbl = new JLabel();
        accessLogPtrnInput = new JTextField();
        saveBtn = new JButton();
        catalinaInstallDirs = new JComboBox<>();
        loadConfigsBtn = new JButton();
        loadInstallDirsBtn = new JButton();
        loadCachedBtn = new JButton();
        runSelectTomcatBtn = new JButton();
        setSelectTcAsCatHome = new JButton();
        jLabel1 = new JLabel();
        jScrollPane1 = new JScrollPane();
        messageLbl = new JLabel();
        debugModeChkBox = new JCheckBox();
        jLabel2 = new JLabel();
        jpdaPortInput = new JTextField();
        restoreBkpBtn = new JButton();
        backupBtn = new JButton();
        stopSelectTomcatBtn = new JButton();

        jMenuItem1.setText("jMenuItem1");

        jMenuItem2.setText("jMenuItem2");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        connectionTimeOutLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        connectionTimeOutLbl.setText("CONNECTION TIMEOUT");

        catalinaHomeLabel.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        catalinaHomeLabel.setText("CATALINA DIRS");

        connectorPortInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        connectorPortInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectorPortInputActionPerformed(evt);
            }
        });

        connectorPortLbl1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        connectorPortLbl1.setText("CONNECTOR PORT");

        connectionTimeOutInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        connectionTimeOutInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectionTimeOutInputActionPerformed(evt);
            }
        });

        accessLogPtrnLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        accessLogPtrnLbl.setText("ACCESS LOG PATTERN");

        accessLogPtrnInput.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        accessLogPtrnInput.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accessLogPtrnInputActionPerformed(evt);
            }
        });

        saveBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        saveBtn.setText("Save Configs");
        saveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveBtnActionPerformed(evt);
            }
        });

        loadConfigsBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        loadConfigsBtn.setText("Load Configs");
        loadConfigsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadConfigsBtnActionPerformed(evt);
            }
        });

        loadInstallDirsBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        loadInstallDirsBtn.setText("Load Install Dirs (Fresh)");
        loadInstallDirsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadInstallDirsBtnActionPerformed(evt);
            }
        });

        loadCachedBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        loadCachedBtn.setText("Load Cached Install Dirs");
        loadCachedBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadCachedBtnActionPerformed(evt);
            }
        });

        runSelectTomcatBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        runSelectTomcatBtn.setText("Run Selected Tomcat");
        runSelectTomcatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                runSelectTomcatBtnActionPerformed(evt);
            }
        });

        setSelectTcAsCatHome.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        setSelectTcAsCatHome.setText("Set Selected Tomcat as CATALINA_HOME");
        setSelectTcAsCatHome.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                setSelectTcAsCatHomeActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel1.setText("Message:");

        messageLbl.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        messageLbl.setForeground(new java.awt.Color(255, 0, 0));
        messageLbl.setBorder(BorderFactory.createLineBorder(new java.awt.Color(0, 0, 153), 2));
        jScrollPane1.setViewportView(messageLbl);

        debugModeChkBox.setText("Run Selected In Debug Mode");
        debugModeChkBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debugModeChkBoxActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        jLabel2.setText("JPDA_PORT");

        jpdaPortInput.setFont(new java.awt.Font("Arial", 0, 12)); // NOI18N

        restoreBkpBtn.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        restoreBkpBtn.setText("Restore Backup Configs");
        restoreBkpBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                restoreBkpBtnActionPerformed(evt);
            }
        });

        backupBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        backupBtn.setText("Backup Configs");
        backupBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backupBtnActionPerformed(evt);
            }
        });

        stopSelectTomcatBtn.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        stopSelectTomcatBtn.setText("Stop Selected Tomcat");
        stopSelectTomcatBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopSelectTomcatBtnActionPerformed(evt);
            }
        });

        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(loadConfigsBtn, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                            .addComponent(backupBtn, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                            .addComponent(loadCachedBtn, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addComponent(saveBtn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                            .addComponent(restoreBkpBtn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)
                            .addComponent(loadInstallDirsBtn, GroupLayout.Alignment.TRAILING, GroupLayout.PREFERRED_SIZE, 226, GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 78, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(connectorPortLbl1, GroupLayout.PREFERRED_SIZE, 162, GroupLayout.PREFERRED_SIZE))
                            .addComponent(accessLogPtrnLbl, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE))
                        .addGap(40, 40, 40)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING, false)
                                .addComponent(catalinaInstallDirs, 0, 420, Short.MAX_VALUE)
                                .addComponent(accessLogPtrnInput))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(connectionTimeOutInput, GroupLayout.Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 73, Short.MAX_VALUE)
                                    .addComponent(connectorPortInput, GroupLayout.Alignment.LEADING))
                                .addGap(60, 60, 60)
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addComponent(jpdaPortInput, GroupLayout.PREFERRED_SIZE, 72, GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(debugModeChkBox, GroupLayout.PREFERRED_SIZE, 223, GroupLayout.PREFERRED_SIZE))))
                    .addComponent(connectionTimeOutLbl, GroupLayout.PREFERRED_SIZE, 192, GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(1, 1, 1)
                        .addComponent(runSelectTomcatBtn, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)
                        .addGap(24, 24, 24)
                        .addComponent(setSelectTcAsCatHome, GroupLayout.PREFERRED_SIZE, 362, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(stopSelectTomcatBtn, GroupLayout.PREFERRED_SIZE, 225, GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(16, 16, 16)
                    .addComponent(catalinaHomeLabel, GroupLayout.PREFERRED_SIZE, 253, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(601, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(41, 41, 41)
                .addComponent(catalinaInstallDirs, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(connectorPortInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectorPortLbl1, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2)
                    .addComponent(jpdaPortInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                    .addComponent(debugModeChkBox))
                .addGap(49, 49, 49)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(connectionTimeOutLbl, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(connectionTimeOutInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addComponent(accessLogPtrnLbl, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addComponent(accessLogPtrnInput, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addComponent(jLabel1, GroupLayout.PREFERRED_SIZE, 39, GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 41, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, GroupLayout.PREFERRED_SIZE, 86, GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(loadInstallDirsBtn)
                    .addComponent(loadCachedBtn))
                .addPreferredGap(LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backupBtn)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(loadConfigsBtn, GroupLayout.PREFERRED_SIZE, 27, GroupLayout.PREFERRED_SIZE)
                            .addComponent(saveBtn))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(setSelectTcAsCatHome)
                            .addComponent(runSelectTomcatBtn)
                            .addComponent(stopSelectTomcatBtn)))
                    .addComponent(restoreBkpBtn))
                .addGap(25, 25, 25))
            .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(35, 35, 35)
                    .addComponent(catalinaHomeLabel, GroupLayout.PREFERRED_SIZE, 30, GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(517, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void connectorPortInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectorPortInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_connectorPortInputActionPerformed

    private void connectionTimeOutInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connectionTimeOutInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_connectionTimeOutInputActionPerformed

    private void accessLogPtrnInputActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_accessLogPtrnInputActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_accessLogPtrnInputActionPerformed

    private void saveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveBtnActionPerformed
        if(catalinaInstallDirs.getSelectedItem()!=null && configurations != null) {
            String tomcatDir = catalinaInstallDirs.getSelectedItem().toString();
            ConfigurationDTO newConfig = fillConfigDTO();
            ServiceResponse response = configServiceClient.saveChangesInServerXML(tomcatDir, newConfig);
            this.messageLbl.setText(response.getMessage());
        } else {
            this.messageLbl.setText("Error: Tomcat not selected or configs not loaded");
        }
    }//GEN-LAST:event_saveBtnActionPerformed

    private void loadInstallDirsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadInstallDirsBtnActionPerformed
        ServiceResponse response = configServiceClient.getTomcatInstallDirs();
        List configDirs =  response.getDataList();
        DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel<>(configDirs.toArray());
        this.catalinaInstallDirs.setModel(comboBoxModel);
        messageLbl.setText(response.getMessage());
    }//GEN-LAST:event_loadInstallDirsBtnActionPerformed

    private void loadConfigsBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if(catalinaInstallDirs.getSelectedItem()!=null) {
            ServiceResponse response = configServiceClient.getConfigurations(catalinaInstallDirs.getSelectedItem().toString());
            ConfigurationDTO cfgs = response.getConfiguration();
            if (cfgs != null) {
                this.configurations = cfgs;
                this.accessLogPtrnInput.setText(cfgs.getAccessLogPattern());
                this.connectionTimeOutInput.setText(String.valueOf(cfgs.getConnectionTimeout()));
                this.connectorPortInput.setText(String.valueOf(cfgs.getConnectorPort()));
            }
            this.messageLbl.setText(response.getMessage());
        } else {
            this.messageLbl.setText("Error: Tomcat not selected");
        }
    }                                              

    private void loadCachedBtnActionPerformed(java.awt.event.ActionEvent evt) {
        ServiceResponse response = configServiceClient.getSavedTomcatInstallDirs();
        List configDirs =  response.getDataList();
        if(configDirs!=null) {
            DefaultComboBoxModel comboBoxModel = new DefaultComboBoxModel<>(configDirs.toArray());
            this.catalinaInstallDirs.setModel(comboBoxModel);
        }
        messageLbl.setText(response.getMessage());
    }                                             

    private void runSelectTomcatBtnActionPerformed(java.awt.event.ActionEvent evt) {
        if(catalinaInstallDirs.getSelectedItem()!=null) {
            String tomcatDir = catalinaInstallDirs.getSelectedItem().toString();
            boolean debugMode = this.debugModeChkBox.isSelected();
            String debugPort = jpdaPortInput.getText();
            ServiceResponse response = configServiceClient.runSelectedTomcat(tomcatDir, debugPort, debugMode);
            this.messageLbl.setText(response.getMessage());
        } else {
            this.messageLbl.setText("Error: Tomcat not selected");
        }
    }                                                  

    private void debugModeChkBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_debugModeChkBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_debugModeChkBoxActionPerformed

    private void backupBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backupBtnActionPerformed
        if(catalinaInstallDirs.getSelectedItem()!=null) {
            String tomcatDir = catalinaInstallDirs.getSelectedItem().toString();
            ServiceResponse response = configServiceClient.createBackupOfServerXML(tomcatDir);
            this.messageLbl.setText(response.getMessage());
        } else {
            this.messageLbl.setText("Error: Tomcat not selected");
        }
    }//GEN-LAST:event_backupBtnActionPerformed

    private void stopSelectTomcatBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopSelectTomcatBtnActionPerformed
        if(catalinaInstallDirs.getSelectedItem()!=null) {
            String tomcatDir = catalinaInstallDirs.getSelectedItem().toString();
            ServiceResponse response = configServiceClient.stopSelectedTomcat(tomcatDir);
            this.messageLbl.setText(response.getMessage());
        } else {
            this.messageLbl.setText("Error: Tomcat not selected");
        }
    }//GEN-LAST:event_stopSelectTomcatBtnActionPerformed

    private void restoreBkpBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_restoreBkpBtnActionPerformed
        if(catalinaInstallDirs.getSelectedItem()!=null) {
            String tomcatDir = catalinaInstallDirs.getSelectedItem().toString();
            ServiceResponse response = configServiceClient.restoreBackupOfServerXML(tomcatDir);
            this.messageLbl.setText(response.getMessage());
        } else {
            this.messageLbl.setText("Error: Tomcat not selected");
        }
    }//GEN-LAST:event_restoreBkpBtnActionPerformed

    private void setSelectTcAsCatHomeActionPerformed(java.awt.event.ActionEvent evt) {
        if(catalinaInstallDirs.getSelectedItem()!=null) {
            ServiceResponse response = configServiceClient.setCatalinaHome(catalinaInstallDirs.getSelectedItem().toString());
            messageLbl.setText(response.getMessage());
        } else{
            this.messageLbl.setText("Error: Tomcat not selected");
        }
    }

    private ConfigurationDTO fillConfigDTO(){
        //only if anything is changed and valid string
        ConfigurationDTO configs = new ConfigurationDTO();
        if(StringUtils.isStringValid(this.connectorPortInput.getText())
                && !configurations.getConnectorPort().equals(Integer.valueOf(this.connectorPortInput.getText()))){
            configs.setConnectorPort(Integer.valueOf(this.connectorPortInput.getText()));
        }
        if(StringUtils.isStringValid(this.connectionTimeOutInput.getText())
                && !configurations.getConnectionTimeout().equals(Integer.valueOf(this.connectionTimeOutInput.getText()))){
            configs.setConnectionTimeout(Integer.valueOf(this.connectionTimeOutInput.getText()));
        }
        if(StringUtils.isStringValid(this.accessLogPtrnInput.getText())
                && !configurations.getAccessLogPattern().equals(this.accessLogPtrnInput.getText())){
            configs.setAccessLogPattern(this.accessLogPtrnInput.getText());
        }
        return configs;
    }

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private JTextField accessLogPtrnInput;
    private JLabel accessLogPtrnLbl;
    private JButton backupBtn;
    private JLabel catalinaHomeLabel;
    private JComboBox<String> catalinaInstallDirs;
    private JTextField connectionTimeOutInput;
    private JLabel connectionTimeOutLbl;
    private JTextField connectorPortInput;
    private JLabel connectorPortLbl1;
    private JCheckBox debugModeChkBox;
    private JLabel jLabel1;
    private JLabel jLabel2;
    private JMenuItem jMenuItem1;
    private JMenuItem jMenuItem2;
    private JScrollPane jScrollPane1;
    private JTextField jpdaPortInput;
    private JButton loadCachedBtn;
    private JButton loadConfigsBtn;
    private JButton loadInstallDirsBtn;
    private JLabel messageLbl;
    private JButton restoreBkpBtn;
    private JButton runSelectTomcatBtn;
    private JButton saveBtn;
    private JButton setSelectTcAsCatHome;
    private JButton stopSelectTomcatBtn;

    private ConfigurationDTO configurations = null;
    // End of variables declaration//GEN-END:variables
}
