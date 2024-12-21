package com.credai.cacheapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.format.annotation.DurationFormat.Unit;
import org.springframework.stereotype.Service;


@Service
public class RedisServiceImpl implements RedisService{

	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	@Override
	public String addDataToRedis(String key, String value) {
		try {
			redisTemplate.opsForValue().set(key, value);
			return "success";
		}catch(Exception e) {
			return e.getLocalizedMessage();
		}
	}

	@Override
	public String getDataFromRedis(String key) {
		try {
			String value = redisTemplate.opsForValue().get(key);
			return value==null?"value is empty":value;
		}catch(Exception e) {
			return "Error Caught";
		}
	}

	@Override
	public String deleteFromRedis(String key) {
		if(redisTemplate.delete(key)) {
		return "sucess";
		}
		else {
			return "failed";
		}
	}

}
