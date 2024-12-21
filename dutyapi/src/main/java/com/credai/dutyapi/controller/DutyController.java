package com.credai.dutyapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.credai.dutyapi.entity.Employee;
import com.credai.dutyapi.service.DutyService;

@RestController
public class DutyController {
	@Autowired
	private DutyService dutyService;
	
	@PostMapping("/api/employee")
	public Employee createEmployee(@RequestBody Employee emp) {
		Employee employee = dutyService.createEmployee(emp);
		return employee;
	}
	
	@GetMapping("/api/employee")
	public List<Employee> getAllEmployee(){
		List<Employee> empList = dutyService.getAllEmployee();
		return empList;
	}
	
	@GetMapping("/api/employee/{empId}")
	public Employee getEmployee(@PathVariable long empId) {
		Employee employee = dutyService.getEmployee(empId);
		return employee;
	}
	
	@PutMapping("/api/employee/{empId}")
	public Employee updateEmployee(@PathVariable long empId, @RequestBody Employee emp) {
		Employee employee = dutyService.updateEmployee(empId, emp);
		return employee;
	}
	
	@DeleteMapping("/api/employee/{empId}")
	public boolean deleteEmployee(@PathVariable long empId) {
		boolean isDeleted = dutyService.deleteEmployee(empId);
		return isDeleted;
	}
}
