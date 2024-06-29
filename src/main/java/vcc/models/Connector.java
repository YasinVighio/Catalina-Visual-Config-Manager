package vcc.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Connector {
    @XmlAttribute
    private int port;

    @XmlAttribute
    private String protocol;

    @XmlAttribute(name = "connectionTimeout")
    private int connectionTimeout;

    @XmlAttribute
    private int redirectPort;

    @XmlAttribute
    private int maxParameterCount;
}
