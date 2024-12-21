package com.credai.cacheapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;


@Service
public class CacheManagerServiceImpl implements CacheManagerService{

	private String cacheName = "userData";
	
	@Autowired
	private CacheManager cacheManager;
	
	@Override
	public String addDataToCacheUsingCacheManager(String key, String value) {
		Cache cache = cacheManager.getCache(cacheName);
		if(cache!=null) {
			cache.put(key, value);
			return "added successfully";
		}
		return "adding failed";
	}

	@Override
	public String getDataFromCacheUsingCacheManager(String key) {
		Cache cache = cacheManager.getCache(cacheName);
		if(cache != null) {
			Cache.ValueWrapper valueWrapper = cache.get(key);
			if(valueWrapper != null) {
				return (String) valueWrapper.get();
			}
		}
		return "error captured";
	}

	@Override
	public String deleteFromCacheUsingCacheManager(String key) {
		Cache cache = cacheManager.getCache(cacheName);
		if(cache != null) {
			cache.evict(key);
			return "deleted successsfully";
		}
		return "deletion failed";
	}

}
