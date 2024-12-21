package com.credai.cacheapi.model;

import org.springframework.stereotype.Component;

import lombok.Data;

@Data
@Component
public class CacheDto {

	private String key;
	private String data;
}
