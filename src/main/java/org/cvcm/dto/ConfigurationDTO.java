package org.cvcm.dto;


import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ConfigurationDTO {
    private List<String> tomcatDirs = new ArrayList<>();
    private Boolean isCatalinaHomeConfigured;
    private String catalinaHome;

    private Integer connectorPort;
    private Integer shutdownPort;
    private Integer connectionTimeout;

    private String accessLogPattern;
}
