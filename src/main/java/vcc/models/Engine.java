package vcc.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Engine {
    @XmlAttribute
    private String name;

    @XmlAttribute
    private String defaultHost;

    @XmlElement(name = "Realm")
    private Realm realm;

    @XmlElement(name = "Host")
    private Host host;
}
