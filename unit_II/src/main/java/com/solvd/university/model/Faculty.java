package com.solvd.university.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.sql.Date;

@XmlRootElement(name = "Faculty")
public class Faculty {
    @XmlAttribute(name = "id")
    private Long id;
    @XmlElement(name = "name")
    private String name;
    @XmlElement(name = "createdAT")
    private Date createdAd;
    @XmlElement(name = "deletedAt")
    private Date deletedAd;

    public Faculty() {
    }

    public Faculty(String name) {
        this.name = name;
    }

    public Faculty(Long id, String name, Date createdAd, Date deletedAd) {
        this.id = id;
        this.name = name;
        this.createdAd = createdAd;
        this.deletedAd = deletedAd;
    }

    @Override
    public String toString() {
        return "Faculty{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", createdAd=" + createdAd +
                ", deletedAd=" + deletedAd +
                '}';
    }
}
