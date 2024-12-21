package com.credai.dutyapi.service;

import java.util.List;

import com.credai.dutyapi.entity.Employee;

public interface DutyService {
	
	public Employee createEmployee(Employee emp);
	
	public List<Employee> getAllEmployee();
	
	public Employee getEmployee(long empId);
	
	public Employee updateEmployee(long empId, Employee emp);
	
	public boolean deleteEmployee(long empId);
}
