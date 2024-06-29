package vcc.models;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlAttribute;
import lombok.Data;

@Data
@XmlAccessorType(XmlAccessType.FIELD)
public class Valve {
    @XmlAttribute
    private String className;

    @XmlAttribute
    private String director;

    @XmlAttribute
    private String prefix;

    @XmlAttribute
    private String suffix;

    @XmlAttribute
    private String pattern;
}
