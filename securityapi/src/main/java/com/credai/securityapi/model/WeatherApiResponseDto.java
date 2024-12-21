package com.credai.securityapi.model;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class WeatherApiResponseDto {
	
	private List<WeatherDto> weather;
	private MainDto main;
	private WindDto wind;
}
