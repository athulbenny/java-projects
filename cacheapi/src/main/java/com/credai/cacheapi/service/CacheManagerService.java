package com.credai.cacheapi.service;

public interface CacheManagerService {

	public String addDataToCacheUsingCacheManager(String key, String value);
	
	public String getDataFromCacheUsingCacheManager(String key);
	
	public String deleteFromCacheUsingCacheManager(String key);
}
