package com.project.springboot;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;

public class Category {
    @XmlAttribute
    private String id;

    @XmlElement(name = "nameC")
    private String name;
    private int ageRecommendation;

    @XmlElement(name = "ageRec")
    public String getId() {
        return id;
    }

    public Category(){

    }

    public Category(String id, String name, int ageRecommendation) {
        this.id = id;
        this.name = name;
        this.ageRecommendation = ageRecommendation;
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

    public int getAgeRecommendation() {
        return ageRecommendation;
    }

    public void setAgeRecommendation(int ageRecommendation) {
        this.ageRecommendation = ageRecommendation;
    }
}
