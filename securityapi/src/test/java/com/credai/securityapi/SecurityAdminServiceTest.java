package com.credai.securityapi;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.credai.securityapi.entity.City;
import com.credai.securityapi.entity.Role;
import com.credai.securityapi.entity.SUser;
import com.credai.securityapi.model.CityDto;
import com.credai.securityapi.model.UserDto;
import com.credai.securityapi.model.WeatherApiResponseDto;
import com.credai.securityapi.repository.CityRepository;
import com.credai.securityapi.repository.RoleRepository;
import com.credai.securityapi.repository.UserRepository;
import com.credai.securityapi.service.SecurityAdminServiceImpl;
import com.credai.securityapi.service.WeatherService;

public class SecurityAdminServiceTest {

	private final Logger logger = LoggerFactory.getLogger(SecurityAdminServiceImpl.class);
	
	@Mock
	private UserRepository userRepository;
	
	@Mock
	private RoleRepository roleRepository;
	
	@Mock
	private CityRepository cityRepository;
	
	@Mock
	private PasswordEncoder passwordEncoder;
	
	@Mock 
	private WeatherService weatherService;
	
	private SecurityAdminServiceImpl securityAdminServiceImpl;
	
	@BeforeEach
	void setUp() {
		
		MockitoAnnotations.openMocks(this);
		
		securityAdminServiceImpl = new SecurityAdminServiceImpl(
						passwordEncoder,cityRepository,roleRepository,userRepository, weatherService);
	}
	
	@Test
	public void testAddNewUser1() {
				
		UserDto user = new UserDto((long)100,"aswanth@gmail.com","samanyu","abcd");
		when(roleRepository.findByRole("USER")).thenReturn(new Role((long)2,"USER",new ArrayList()));
		when(userRepository.findByUsername("samanyu")).thenReturn(Optional.empty());
		boolean value = securityAdminServiceImpl.addNewUserToDb(user);        
		assertTrue(value);
	}
	
	
	@Test
	public void testAddNewUser2() {
				
		UserDto user = new UserDto((long)100,"aswanth@gmail.com","samanyu","abcd");
		when(roleRepository.findByRole("USER")).thenReturn(new Role((long)2,"USER",new ArrayList()));
		when(userRepository.findByUsername("samanyu")).thenReturn(Optional.of(new SUser((long)1,"samanyu","aswanth@gmail.com","abcd",new Role((long)2,"USER",new ArrayList()))));
		boolean value = securityAdminServiceImpl.addNewUserToDb(user);        
		assertFalse(value);
	}
	
	@Test
	public void testAddNewAdmin1() {
				
		UserDto user = new UserDto((long)100,"aswanth@gmail.com","samanyu","syuh");
		when(roleRepository.findByRole("ADMIN")).thenReturn(new Role((long)1,"ADMIN",new ArrayList()));
		when(userRepository.findByUsername("samanyu")).thenReturn(Optional.empty());
		boolean value = securityAdminServiceImpl.addNewAdminToDb(user);        
		assertTrue(value);
	}
	
	@Test
	public void testAddNewAdmin2() {
				
		UserDto user = new UserDto((long)100,"aswanth@gmail.com","samanyu","abcd");
		when(roleRepository.findByRole("ADMIN")).thenReturn(new Role((long)1,"ADMIN",new ArrayList()));
		when(userRepository.findByUsername("samanyu")).thenReturn(Optional.of(new SUser((long)1,"samanyu","aswanth@gmail.com","abcd",new Role((long)1,"ADMIN",new ArrayList()))));
		boolean value = securityAdminServiceImpl.addNewAdminToDb(user);        
		assertFalse(value);
	}
	
	@Test
	public void testAddOrRemoveCity1() {
		
		CityDto cityDto = new CityDto("KANNUR","add");
		when(weatherService.getWeather("KANNUR")).thenReturn(new WeatherApiResponseDto());
		when(cityRepository.findByCity("KANNUR")).thenReturn(Optional.empty());
		boolean value = securityAdminServiceImpl.addOrRemoveCity(cityDto);
		assertTrue(value);
	}
	
	@Test
	public void testAddOrRemoveCity2() {
		
		CityDto cityDto = new CityDto("KANNUR","add");
		when(cityRepository.findByCity("KANNUR")).thenReturn(Optional.of(new City((long)10,"KANNUR")));
		boolean value = securityAdminServiceImpl.addOrRemoveCity(cityDto);
		assertFalse(value);
	}
	
	@Test
	public void testAddOrRemoveCity3() {
		
		CityDto cityDto = new CityDto("KANNUR","remove");
		when(cityRepository.findByCity("KANNUR")).thenReturn(Optional.empty());
		boolean value = securityAdminServiceImpl.addOrRemoveCity(cityDto);
		assertFalse(value);
	}
	
	@Test
	public void testAddOrRemoveCity4() {
		
		CityDto cityDto = new CityDto("KANNUR","remove");
		when(cityRepository.findByCity("KANNUR")).thenReturn(Optional.of(new City((long)10,"KANNUR")));
		boolean value = securityAdminServiceImpl.addOrRemoveCity(cityDto);
		assertTrue(value);
	}
	
}
