package com.credai.securityapi.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.credai.securityapi.model.AuthResponseDto;
import com.credai.securityapi.model.LoginDto;
import com.credai.securityapi.model.UserDto;
import com.credai.securityapi.service.AuthService;
import com.credai.securityapi.service.SecurityAdminService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;


@Controller
public class SecurityController {

	
	private final Logger logger = LoggerFactory.getLogger(SecurityController.class);

	@Autowired
	private AuthService authService;

	@Autowired
	private SecurityAdminService securityAdminService;
	
//	private JwtUtil jwtUtil; 
	
	@GetMapping("/signup")
	public String signup(Model model) {
		model.addAttribute("user",new UserDto());
		return "signup";
	}
	
	
	@GetMapping("/signuperror")
	public String signuperror(Model model) {
//        String errorMessage = "An unexpected error occurred.";
        String errorDetails = "Error can be :\n 1.\tUser already exist \n 2.\tPassword is lesser than eight characters \n 3.\tInvalid email id";
        model.addAttribute("errorMessage", "An unexpected error occurred.");
        model.addAttribute("errorDetails", errorDetails);

        return "signuperror";
    }
	
	@GetMapping("/signin")
	public String signin(Model model) {
		model.addAttribute("loginDto", new LoginDto());
		return "signin";
	}
	
	@PostMapping("/signup")
	public String signupDo(@ModelAttribute UserDto user, Model model ) {
		
		if(securityAdminService.addNewUserToDb(user) && user.getPassword().length()>=8) {
	  		  model.addAttribute("loginDto", new LoginDto());
	  		  model.addAttribute("success","Signup successful");
			return "signin";
		}else {
			return "redirect:/signuperror";
		}
	}
	
	@PostMapping("/signin")
    public String signin(
    		@ModelAttribute LoginDto loginDto,
    		Model model, HttpServletResponse response,
    		@RequestParam(value = "error", required = false) String error){
	  try {	
        String token = authService.login(loginDto);

        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setAccessToken(token);
        logger.info("token is : {}", token);
        
        Cookie cookie = new Cookie("jwt",token);
        cookie.setMaxAge(7*24*60*60);//in seconds
        cookie.setHttpOnly(true);
        cookie.setPath("/");
        response.addCookie(cookie);
       

        return  "redirect:/suser";//new ResponseEntity<>(authResponseDto, HttpStatus.OK);
	  }catch(BadCredentialsException e){
          model.addAttribute("error", "Invalid username or password. Please try again.");
  		  model.addAttribute("loginDto", new LoginDto());

          return "signin";
		  }
	  }

	  @GetMapping("/logout")
	    public String logout(HttpServletResponse response) {
		  
	        return "redirect:/signin"; 
	    }
	
}
