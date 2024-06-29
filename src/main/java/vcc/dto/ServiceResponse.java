package vcc.dto;

import lombok.Data;

import java.util.List;

@Data
public class ServiceResponse {
    private List dataList;
    private ConfigurationDTO configuration;
    private String message;
}
