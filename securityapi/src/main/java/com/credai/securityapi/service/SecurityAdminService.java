package com.credai.securityapi.service;

import java.util.List;

import com.credai.securityapi.model.CityDto;
import com.credai.securityapi.model.UserDto;

public interface SecurityAdminService {
	

	public boolean addNewUserToDb(UserDto user);

	public boolean addNewAdminToDb(UserDto user);
	
	public boolean addOrRemoveCity(CityDto city);

	public List<String> getPlacesAddedByAdmin();
}
