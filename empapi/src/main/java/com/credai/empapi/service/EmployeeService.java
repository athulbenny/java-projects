package com.credai.empapi.service;

import java.util.List;

import com.credai.empapi.entity.Employee;

public interface EmployeeService {
	
	public Employee createEmployee(Employee employee);
	
	public List<Employee> getAllEmployee();
	
	public Employee getEmployee(long empId);
	
	public Employee updateEmployee(long empId, Employee employee);
	
	public boolean deleteEmployee(long empId);
}
