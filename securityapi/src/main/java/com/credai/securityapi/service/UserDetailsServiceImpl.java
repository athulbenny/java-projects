package com.credai.securityapi.service;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.credai.securityapi.entity.SUser;
import com.credai.securityapi.repository.UserRepository;

import lombok.Data;

//https://medium.com/@Lakshitha_Fernando/spring-security-6-and-spring-boot-3-with-simple-project-91389cc13119


@Service
@Data
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	SUser user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String role = user.getRole().getRole();
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), 
                user.getPassword(),
                Collections.singleton(new SimpleGrantedAuthority(role))
        );
    }
}


