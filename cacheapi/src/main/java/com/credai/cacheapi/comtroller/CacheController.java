package com.credai.cacheapi.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.credai.cacheapi.model.CacheDto;
import com.credai.cacheapi.service.CacheService;

@RestController
@RequestMapping("/api/v1")
public class CacheController {

	@Autowired
	private CacheService cacheService;
	
	@PostMapping()
	public ResponseEntity<Object> addDataToCache(@RequestBody CacheDto cacheDto){
		
		String str = cacheService.addDataToCache(cacheDto);
		return new ResponseEntity<>(str,HttpStatus.ACCEPTED);
	}
	
	@PutMapping()
	public ResponseEntity<Object> updateDataToCache(@RequestBody CacheDto cacheDto){
		
		String str = cacheService.updateDataFromCache(cacheDto);
		return new ResponseEntity<>(str,HttpStatus.ACCEPTED);
	}

	
	@DeleteMapping("/{key}")
	public ResponseEntity<Object> deleteDataFromCache(@PathVariable String key){
		cacheService.deleteDataFromCache(key);
		return new ResponseEntity<>("deletion successfully ended",HttpStatus.ACCEPTED);
	}
	

}
