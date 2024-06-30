package org.cvcm.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@Data
@XmlRootElement(name = "Server")
@XmlAccessorType(XmlAccessType.FIELD)
public class Server {
    @XmlAttribute
    private int port;

    @XmlAttribute
    private String shutdown;

    @XmlElement(name = "Listener")
    private Listener[] listeners;

    @XmlElement(name = "GlobalNamingResources")
    private GlobalNamingResources globalNamingResources;

    @XmlElement(name = "Service")
    private Service service;
}
