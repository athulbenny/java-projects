package com.credai.batchapi.model;

import jakarta.xml.bind.annotation.XmlElement;

public class Errors {

	private long number;
	private String source;
	private String description;
	
	 @XmlElement(name = "Number")
	    public long getNumber() {
	        return number;
	    }

	    public void setNumber(long number){
	        this.number = number; 
	    }
	    
	 @XmlElement(name = "Source")
	    public String getSource() {
	        return source;
	    }

	    public void setSource(String source){
	        this.source = source; 
	    }
	    
	    @XmlElement(name = "Description")
	    public String getDescription() {
	        return description;
	    }

	    public void setDescription(String description){
	        this.description = description; 
	    }
}
