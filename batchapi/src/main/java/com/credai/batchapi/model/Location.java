package com.credai.batchapi.model;

public record Location (

	String id,
	String location,
	String street,
	String city,
	String state,
	String zipcode,
	String lat,
	String lng	) 
{}

