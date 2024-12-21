package com.credai.cacheapi.service;

import com.credai.cacheapi.model.CacheDto;

public interface CacheService {

	public String addDataToCache(CacheDto cacheDto);
		
	public String updateDataFromCache(CacheDto cacheDto);
	
	public void deleteDataFromCache(String key);
	
	public String updateDataBetweenCache(CacheDto cacheDto);
	
}
