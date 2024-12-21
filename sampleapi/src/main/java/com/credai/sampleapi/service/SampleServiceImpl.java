package com.credai.sampleapi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credai.sampleapi.Repository.SampleRepository;
import com.credai.sampleapi.entity.Sample;
import com.credai.sampleapi.exception.UserNotFoundException;

@Service
public class SampleServiceImpl implements SampleService{
	@Autowired
	private SampleRepository sampleRepository;
	
	@Override
	public Sample createSample(Sample sampleInput) {
		Sample sample = sampleRepository.save(sampleInput);
		return sample;
	}

	@Override
	public List<Sample> getAllSample() {
		List<Sample> sampleList = sampleRepository.findAll();
		return sampleList;
	}

	@Override
	public Sample getSample(long sampleId) throws UserNotFoundException{
		Optional opt = sampleRepository.findById(sampleId);
		if(!opt.isPresent()) {
			throw new UserNotFoundException("user with id: "+sampleId+" is not present");
		}
		Sample sample = sampleRepository.findById(sampleId).get();
		return sample;
	}

	@Override
	public Sample updateSample(long sampleId, Sample sampleInput) {
		sampleInput.setUserId(sampleId);
		Sample sample = sampleRepository.save(sampleInput);
		return sample;
	}

	@Override
	public boolean deleteSample(long sampleId) {
		if(sampleRepository.findById(sampleId).isPresent()) {
			sampleRepository.deleteById(sampleId);
			return true;
		}
		return false;
	}

}

