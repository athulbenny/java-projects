package com.credai.cacheapi.comtroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.credai.cacheapi.model.CacheDto;
import com.credai.cacheapi.service.RedisService;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping("/api/v3")
public class RedisController {

	@Autowired
	private RedisService redisService;
	
	@PostMapping
	public ResponseEntity<Object> addDataToRedis(@RequestBody CacheDto cacheDto){
		String result = redisService.addDataToRedis(cacheDto.getKey(), cacheDto.getData());
		return new ResponseEntity<Object>(result,HttpStatus.ACCEPTED);
	}
	

	@GetMapping("/{key}")
	public ResponseEntity<Object> getDataFromRedis(@PathVariable String key){
		String result = redisService.getDataFromRedis(key);
		return new ResponseEntity<Object>(result,HttpStatus.ACCEPTED);
	}
	
	
	@DeleteMapping("{key}")
	public ResponseEntity<Object> addDataToRedis(@PathVariable String key){
		String result = redisService.deleteFromRedis(key);
		return new ResponseEntity<Object>(result,HttpStatus.ACCEPTED);
	}
}
