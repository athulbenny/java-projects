package com.credai.batchapi.model;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.xml.bind.annotation.XmlType;


@XmlRootElement(name = "CityStateLookupResponse")
@XmlType(propOrder = {"zipCode"})
public class CityStateLookupResponse {

    private ZipCode zipCode;

    @XmlElement(name = "ZipCode")
    public ZipCode getZipCode() {
        return zipCode;
    }

    public void setZipCode(ZipCode zipCode) {
        this.zipCode = zipCode;
    }
}
