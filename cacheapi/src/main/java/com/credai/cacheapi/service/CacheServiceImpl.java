package com.credai.cacheapi.service;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import com.credai.cacheapi.model.CacheDto;

@CacheConfig(cacheNames="userData")
//@CacheConfig(cacheNames={"userData", "tempData"})
@Service
public class CacheServiceImpl implements CacheService{

	
	@Override
	@Cacheable(key ="#cacheDto.key")
	public String addDataToCache(CacheDto cacheDto) { // parameter and key should be same
		if(cacheDto.getKey()!=null) {
			System.out.println(cacheDto.getKey());
			return cacheDto.getData();
		}else {
			System.out.println("null is caught");
			return "Error caught";
		}
	}
	
	@Override
	@CacheEvict(key="#key")
	public void deleteDataFromCache(String key) {
		System.out.println("deleted");
	}

	@Override
	@CachePut(key = "#cacheDto.key")
	public String updateDataFromCache(CacheDto cacheDto) {
		return cacheDto.getData();
	}

	@Override
	@Caching(
			  put = @CachePut(value = "userData", key = "#cacheDto.key"),
			  evict = @CacheEvict(value = "tempData", key = "#cacheDto.key")
			)	
	public String updateDataBetweenCache(CacheDto cacheDto) {
		return cacheDto.getData();
		
	}
	


}
