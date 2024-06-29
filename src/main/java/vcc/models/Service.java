package vcc.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Service {
    @XmlAttribute
    private String name;

    @XmlElement(name = "Connector")
    private Connector[] connectors;

    @XmlElement(name = "Engine")
    private Engine engine;
}
