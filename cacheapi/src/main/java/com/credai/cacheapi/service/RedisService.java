package com.credai.cacheapi.service;

public interface RedisService {

	public String addDataToRedis(String key, String value);
	
	public String getDataFromRedis(String key);
	
	public String deleteFromRedis(String key);
}
