package com.credai.securityapi.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.logging.log4j.spi.LoggerContextFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.credai.securityapi.controller.SecurityController;
import com.credai.securityapi.entity.City;
import com.credai.securityapi.entity.Role;
import com.credai.securityapi.entity.SUser;
import com.credai.securityapi.model.CityDto;
import com.credai.securityapi.model.UserDto;
import com.credai.securityapi.repository.CityRepository;
import com.credai.securityapi.repository.RoleRepository;
import com.credai.securityapi.repository.UserRepository;


@Service
public class SecurityAdminServiceImpl implements SecurityAdminService{

	@Autowired
	private WeatherService weatherService;
	
	@Autowired
    private  PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepository roleRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private CityRepository cityRepository;
	
	
	public SecurityAdminServiceImpl(
			PasswordEncoder passwordEncoder, 
			CityRepository cityRepository,
			RoleRepository roleRepository, 
			UserRepository userRepository,
			WeatherService weatherService) {
		this.passwordEncoder = passwordEncoder;
		this.cityRepository = cityRepository;
		this.userRepository = userRepository;
		this.roleRepository = roleRepository;
		this.weatherService = weatherService;
	}
	
	@Override
	public boolean addNewUserToDb(UserDto user) {
		try {
		Role role = roleRepository.findByRole("USER");
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        role.setRoleId((long)2);
        
        List<String> userList = role.getUserSet();
        userList.add(user.getUsername());
        role.setUserSet(userList);
        
        Optional<SUser> checkUser = userRepository.findByUsername(user.getUsername());
        
        
        if(!checkUser.isPresent()) {
        	SUser userEntity = new SUser();
			userEntity.setRole(role);
			userEntity.setEmail(user.getEmail());
			userEntity.setUsername(user.getUsername());
			userEntity.setPassword(encodedPassword);
			userRepository.save(userEntity);
			roleRepository.save(role);
			return true;
        }
        return false;
	}
		catch(Exception e) {
			return false;
		}
	}
	
	
	@Override
	public boolean addNewAdminToDb(UserDto user) {
		try {
			Role role = roleRepository.findByRole("ADMIN");
	        String encodedPassword = passwordEncoder.encode(user.getPassword());
	        role.setRoleId((long)1);
	        
	        List<String> userList = role.getUserSet();
	        userList.add(user.getUsername());
	        role.setUserSet(userList);
	        
	        Optional<SUser> checkUser = userRepository.findByUsername(user.getUsername());
	        
	        if(!checkUser.isPresent()) {
	        	SUser userEntity = new SUser();
				userEntity.setRole(role);
				userEntity.setUsername(user.getUsername());
				userEntity.setEmail(user.getEmail());
				userEntity.setPassword(encodedPassword);
				userRepository.save(userEntity);
				roleRepository.save(role);
				return true;
	        }
	        return false;
		}
			catch(Exception e) {
				return false;
			}
	}
	
	public boolean addOrRemoveCity(CityDto cityDto) {
		try {
			Optional<City> hasCity = cityRepository.findByCity(cityDto.getCityName().trim().toUpperCase());
			if(hasCity.isEmpty() && cityDto.getAction().equals("add")) {
				if(weatherService.getWeather(cityDto.getCityName().trim())!=null) {
					City city = new City();
					city.setCity(cityDto.getCityName().trim().toUpperCase());
					cityRepository.save(city);
					return true;
				}
			}else if(hasCity.isPresent() && cityDto.getAction().equals("remove")){
				cityRepository.delete(hasCity.get());
			return true;
			}
			
			return false;
		}catch(Exception e) {
			return false;
		}
	}
	
	@Override
	public List<String> getPlacesAddedByAdmin() {
		
		List<City> cities = cityRepository.findAll();
		List<String> cityList = new ArrayList<String>();
		cities.forEach(city-> cityList.add(city.getCity().toUpperCase()));
		
		return cityList;
	}
	

}
