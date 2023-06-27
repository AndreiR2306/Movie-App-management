package com.project.springboot;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Movie {
    @XmlAttribute
    private String id;

    @XmlElement(name = "nameF")
    private String name;

    @XmlElement(name = "year")
    private int year;
    @XmlElement(name = "category-ref")
    private String category;

    @XmlElement(name = "actor-ref")
    private String actor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
