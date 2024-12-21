package com.credai.dutyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.credai.dutyapi.entity.Employee;

public interface DutyRepository extends JpaRepository<Employee, Long>{ 

}
