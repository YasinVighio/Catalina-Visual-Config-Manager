package org.cvcm.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Host {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private String appBase;

    @XmlAttribute
    private boolean unpackWARs;

    @XmlAttribute
    private boolean autoDeploy;

    @XmlElement(name = "Valve")
    private Valve valve;
}
