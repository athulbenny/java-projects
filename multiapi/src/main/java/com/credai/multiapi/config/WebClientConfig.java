package com.credai.multiapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class WebClientConfig {

    @Bean
    WebClient externalApiCLient() {
		return WebClient.create("https://zip-api.eu/api/v1/info/US-");
	}
}
