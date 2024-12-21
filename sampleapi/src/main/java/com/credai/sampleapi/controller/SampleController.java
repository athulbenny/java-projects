package com.credai.sampleapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.credai.sampleapi.entity.Sample;
import com.credai.sampleapi.exception.UserNotFoundException;
import com.credai.sampleapi.service.SampleService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;

@Validated
@RestController
public class SampleController {
	@Autowired
	private SampleService sampleService;
	
	@PostMapping("/api/sample")
	public Sample createSample(@Valid @RequestBody Sample sampleInput) {
		Sample sample = sampleService.createSample(sampleInput);
		return sample;
	}
	
	@GetMapping("/api/sample")
	public List<Sample> getAllSample(){
		List<Sample> sampleList = sampleService.getAllSample();
		return sampleList;
	}
	
	@GetMapping("/api/sample/{sampleId}") 
	public ResponseEntity<Object> getSample(@PathVariable long sampleId) throws UserNotFoundException{
		Sample sample = sampleService.getSample(sampleId);
		if(sample==null) {
			throw new UserNotFoundException("user with id: "+sampleId+" is not present");
		}
		return new ResponseEntity<>(sample, HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/api/sample/{sampleId}")
	public Sample updateSample(@PathVariable long sampleId, @RequestBody Sample sampleInput) {
		Sample sample = sampleService.updateSample(sampleId, sampleInput);
		return sample;
	}
	
	@DeleteMapping("/api/sample/{sampleId}")
	public boolean deleteSample(@PathVariable @Min(2) long sampleId) {
		boolean isDeleted = sampleService.deleteSample(sampleId);
		return isDeleted;
	}
}
