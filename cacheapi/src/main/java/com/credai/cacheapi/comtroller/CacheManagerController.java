package com.credai.cacheapi.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.credai.cacheapi.model.CacheDto;
import com.credai.cacheapi.service.CacheManagerService;

@Controller
@RequestMapping("/api/v2")
public class CacheManagerController {

	@Autowired
	private CacheManagerService cacheManagerService;
	
	@PostMapping
	public ResponseEntity<Object> addDataToCacheUsingCacheManager(@RequestBody CacheDto cacheDto){
		String str = cacheManagerService.addDataToCacheUsingCacheManager(cacheDto.getKey(), cacheDto.getData());
		return new ResponseEntity<>(str,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/{key}")
	public ResponseEntity<Object> getDataFromCache(@PathVariable String key){
		String s = cacheManagerService.getDataFromCacheUsingCacheManager(key);
		return new ResponseEntity<>(s,HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{key}")
	public ResponseEntity<Object> deleteDataFromCacheUsingCacheManager(@PathVariable String key){
		String msg = cacheManagerService.deleteFromCacheUsingCacheManager(key);
		return new ResponseEntity<>(msg,HttpStatus.ACCEPTED);
	}	
}
