package com.credai.securityapi.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.credai.securityapi.model.CityDto;
import com.credai.securityapi.model.UserDto;
import com.credai.securityapi.model.WeatherApiResponseDto;
import com.credai.securityapi.service.AuthService;
import com.credai.securityapi.service.SecurityAdminService;
import com.credai.securityapi.service.WeatherService;

@Controller
@RequestMapping("/suser")
public class AuthController {

	private final Logger logger = LoggerFactory.getLogger(AuthController.class);

	@Autowired
	private WeatherService weatherService;
	
	@Autowired
	private SecurityAdminService securityAdminService;
	
	
    @GetMapping
//    @PreAuthorize("hasRole('ROLR_USER') or hasRole('ROLE_ADMIN')")
    public String home(Model model){
    	
        
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        String role = authentication != null ? authentication.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
                .orElse("user") : "user"; // Default to "user" if no role is found

        logger.info("role is {}", role);
        model.addAttribute("role", role);
        String currentTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        model.addAttribute("currentTime", currentTime);
        
        return "home";
    }


    //admin to add new city
    @GetMapping("/addCity")
    public String addCities(Model model) {
    	model.addAttribute("city", new CityDto());
    	return "addCity";
    }
    
    @PostMapping("/addCity")
    public String addCitiesdo(@ModelAttribute CityDto city, Model model) {
    	boolean addOrRemoveCheckerFlag = securityAdminService.addOrRemoveCity(city);
    	if(addOrRemoveCheckerFlag) {
    		return "redirect:/suser";
    	}else {
    		model.addAttribute("error", "Error in the action! Recheck");
    		model.addAttribute("city", new CityDto());
    		return "addCity";
    	}
    }

    
    

       @GetMapping("/addAdmin")
    public String addAdmin(Model model) {
    	model.addAttribute("user",new UserDto());;
        return "addAdmin"; // You can link to a form for adding admin
    }
    
    @PostMapping("/addAdmin")
    public String addAdminDo(@ModelAttribute UserDto user) {
    	if(securityAdminService.addNewAdminToDb(user)) {
			return "redirect:/suser";
		}else {
			return "redirect:/signuperror";
		}
    }


    
    @GetMapping("/addUser")
    public String addUser(Model model) {
    	model.addAttribute("user",new UserDto());;
        return "addUser"; // You can link to a form for adding user
    }
    
    @PostMapping("/addUser")
    public String addUserDo(@ModelAttribute UserDto user) {
    	if(securityAdminService.addNewUserToDb(user)) {
			return "redirect:/suser";
		}else {
			return "redirect:/signuperror";
		}
    }
    
    
    
    
    @PostMapping("/searchLocation")
    public String searchLocation(@RequestParam String city, Model model) {
    	List<String> cityList = securityAdminService.getPlacesAddedByAdmin();
    	city = city.toUpperCase();
		logger.info("city is in {}",cityList.contains(city));

    	if(cityList.contains(city)) {
    		return getWeather(city, model);
    	}else {
    		model.addAttribute("error", "Locations from the list shown below are only allowed");
    		return addLocation(model);
    	}
    }
    
    
    //addLocation helps user and admin to enter the required location
    @GetMapping("/addLocation")
    public String addLocation(Model model) {
    	
    	List<String> cityList = securityAdminService.getPlacesAddedByAdmin();
    	logger.info("city list is {}", cityList);
    	Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        String role = authentication != null ? authentication.getAuthorities().stream()
                .findFirst()
                .map(grantedAuthority -> grantedAuthority.getAuthority())
        		.orElse("user") : "user";
        
        model.addAttribute("role",role);  
        model.addAttribute("cityList", cityList);
    	return "addLocation";
    }
    
    
    
    @PostMapping("/getWeather")
    public String getWeather(@RequestParam String city, Model model) {
    	try {
	        WeatherApiResponseDto weatherResponse = weatherService.getWeather(city);
	 
	        if(weatherResponse!=null) {
		        model.addAttribute("weatherList", weatherResponse.getWeather());
		        model.addAttribute("mainWeather", weatherResponse.getMain());
		        model.addAttribute("wind", weatherResponse.getWind());
	        }else {
	    		model.addAttribute("error","No such location");
	        }
	        model.addAttribute("name",city);
	        return "getWeather";  // Thymeleaf template name
    	}catch(Exception e) {
    		model.addAttribute("name",city);
    		model.addAttribute("error","No such location");
    		return "getWeather";
    	}
    }
}