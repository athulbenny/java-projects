package com.credai.batchapi.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlAttribute;

public class ZipCode {

    private String id;
    private String zip5;
    private String city;
    private String state;
    private Errors error;

    @XmlAttribute(name = "ID")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    
    @XmlElement(name = "Error")
    public Errors getError() {
        return error;
    }

    public void setError(Errors error) {
        this.error = error;
    }


    @XmlElement(name = "Zip5")
    public String getZip5() {
        return zip5;
    }

    public void setZip5(String zip5) {
        this.zip5 = zip5;
    }

    @XmlElement(name = "City")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @XmlElement(name = "State")
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
