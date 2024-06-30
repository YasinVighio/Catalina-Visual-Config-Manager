package org.cvcm.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class ServiceResponse {
    private List dataList;
    private ConfigurationDTO configuration;
    private String message;
    private Map dataMap;
}
