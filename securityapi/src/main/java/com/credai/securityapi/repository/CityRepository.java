package com.credai.securityapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.securityapi.entity.City;
import java.util.List;
import java.util.Optional;


public interface CityRepository extends JpaRepository<City, Long>{
	
	Optional<City> findByCity(String city);
}
