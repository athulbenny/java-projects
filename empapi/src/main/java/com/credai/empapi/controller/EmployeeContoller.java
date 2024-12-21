package com.credai.empapi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.credai.empapi.entity.Employee;
import com.credai.empapi.service.EmployeeService;

import jakarta.validation.Valid;

@RestController // restful service, if mvc its controller
public class EmployeeContoller {
	@Autowired
	private EmployeeService employeeService;
	
	@PostMapping("/api/employee")
	public Employee createEmployee(@Valid @RequestBody Employee emp) {
		Employee employee = employeeService.createEmployee(emp);
		return employee;
	}
	
	@GetMapping("/api/employee")
	public List<Employee> getAllEmployee(){
		List<Employee> employeeList = employeeService.getAllEmployee();
		return employeeList;
	}
	
	@GetMapping("/api/employee/{empId}")
	public Employee getEmployee(long empId){
		Employee employee = employeeService.getEmployee(empId);
		return employee;
	}
	
	@PutMapping("/api/employee/{empId}")
	public Employee updateEmployee(long empId, Employee emp) {
		Employee employee = employeeService.updateEmployee(empId, emp);
		return employee;
	}
	
	public String deleteEmployee(long empId) {
		boolean value = employeeService.deleteEmployee(empId);
		return (value?"deleted successfully":"error in deletion");
	}
	
}
