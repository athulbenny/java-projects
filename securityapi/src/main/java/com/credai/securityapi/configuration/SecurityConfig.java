package com.credai.securityapi.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.credai.securityapi.components.JwtAuthenticationEntryPoint;
import com.credai.securityapi.components.JwtAuthenticationFilter;

import jakarta.servlet.http.Cookie;
import lombok.AllArgsConstructor;

//@Configuration
//@EnableWebSecurity
//@EnableMethodSecurity
//public class SecurityConfig {
//
//    @Bean
//     PasswordEncoder passwordEncoder(){
//        return new BCryptPasswordEncoder();
//    }
//
//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
//
//        http.csrf(csrf -> csrf.disable())
//                .authorizeHttpRequests((authorize) -> {
//                    authorize.requestMatchers("api/v1/**").permitAll();
//
//                    authorize.anyRequest().authenticated();
//                }).httpBasic(Customizer.withDefaults());
//        return http.build();
//    }
//
//    @Bean
//     UserDetailsService userDetailsService(){
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password(passwordEncoder().encode("john"))
//                .roles("USER")
//                .build();
//
//        UserDetails sam = User.builder()
//                .username("sam")
//                .password(passwordEncoder().encode("sam"))
//                .roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(john,sam);
//    }
//}


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

	@Autowired
    private UserDetailsService userDetailsService;
    
	@Autowired
    private JwtAuthenticationEntryPoint authenticationEntryPoint;
    
    @Autowired
    private JwtAuthenticationFilter authenticationFilter;

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests((authorize) -> {
                	authorize.requestMatchers("/signup","/signin","/signuperror").permitAll();
                    authorize.requestMatchers("/suser/addAdmin","/suser/addUser","/suser/addCity").hasAuthority("ADMIN");//.hasRole("ADMIN");//hasRole chage ADMIN as ROLE_ADMIN
                    authorize.requestMatchers("/suser","/suser/getWeather","/suser/addLocation").authenticated();                
                    authorize.requestMatchers("/suser/searchLocation").hasAnyAuthority("USER");//.hasRole("USER");
                    authorize.anyRequest().authenticated();
                })
//                .formLogin(
//                		form->form
//                		.loginPage("/signin")
//                		.loginProcessingUrl("/signinProcess")        // Custom login processing URL (default is /login)
//                        .defaultSuccessUrl("/suser", true)  // Redirect to dashboard on successful login
//                        .failureUrl("/signin?error=true") 
//                		.permitAll())
                .logout(logout->logout
                		.logoutSuccessUrl("/signin?logout")
                		.permitAll()
                		.addLogoutHandler((request, response, auth)->{
                			Cookie[] cookies = request.getCookies();
                            if (cookies != null) {
                                for (Cookie cookie : cookies) {
                                	if (cookie.getName().equals("jwt")) {
                                    // Set the cookie's max age to 0 to delete it
                                    cookie.setMaxAge(0);
                                    cookie.setPath("/"); // Ensure the path matches the cookie's original path
                                    response.addCookie(cookie);
                                	}// Add the cookie back to the response
                                }
                            }
                }))
                .httpBasic(Customizer.withDefaults()).sessionManagement(session ->
                session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Enforce stateless session management
            );
        
        http.exceptionHandling( exception -> exception
                .authenticationEntryPoint(authenticationEntryPoint));

        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

}