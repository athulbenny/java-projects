package com.credai.sampleapi.service;

import java.util.List;

import com.credai.sampleapi.entity.Sample;
import com.credai.sampleapi.exception.UserNotFoundException;

public interface SampleService {
	
	public Sample createSample(Sample emp);
	
	public List<Sample> getAllSample();
	
	public Sample getSample(long empId) throws  UserNotFoundException;
	
	public Sample updateSample(long empId, Sample emp);
	
	public boolean deleteSample(long empId);
}
