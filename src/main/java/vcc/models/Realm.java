package vcc.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import jakarta.xml.bind.annotation.XmlElement;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Realm {
    @XmlAttribute
    private String className;

    @XmlElement(name = "Realm")
    private Realm nestedRealm;

    @XmlAttribute
    private String resourceName;
}
