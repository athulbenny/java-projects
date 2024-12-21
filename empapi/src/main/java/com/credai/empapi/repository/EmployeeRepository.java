package com.credai.empapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.credai.empapi.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
	
}
