package com.credai.securityapi.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.credai.securityapi.model.WeatherApiResponseDto;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class WeatherService {

	private ObjectMapper objectMapper;
	
	public WeatherApiResponseDto getWeatherFromJson(String jsonResponse) throws Exception {
        return objectMapper.readValue(jsonResponse, WeatherApiResponseDto.class);
    }
	
	public WeatherApiResponseDto getWeather(String cityName){
		RestTemplate restTemplate = new RestTemplate();
		String uri = "https://api.openweathermap.org/data/2.5/weather?q="+cityName+"&appid=805d140e18d72cb75221d4d60d56c12c";
		WeatherApiResponseDto weatherApiResponseDto = restTemplate.getForObject(uri, WeatherApiResponseDto.class);  
		
//		WeatherApiResponseDto weatherApiResponseDto = getWeatherFromJson("");
		return weatherApiResponseDto;
	}
}
