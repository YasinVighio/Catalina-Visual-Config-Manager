package vcc.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Resource {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private String auth;

    @XmlAttribute
    private String type;

    @XmlAttribute
    private String description;

    @XmlAttribute
    private String factory;

    @XmlAttribute
    private String pathname;
}

