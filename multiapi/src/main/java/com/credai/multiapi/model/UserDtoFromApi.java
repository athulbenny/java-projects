package com.credai.multiapi.model;

import org.springframework.stereotype.Component;
import lombok.Data;

@Data
@Component
public class UserDtoFromApi {
	private String place_name;
	private String state;
	private String country_code;
	private String postalCode;
	private String lat;
	private String lng;

}

