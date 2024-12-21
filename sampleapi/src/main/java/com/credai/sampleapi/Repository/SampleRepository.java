package com.credai.sampleapi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.sampleapi.entity.Sample;

public interface SampleRepository extends JpaRepository<Sample, Long> {

}
