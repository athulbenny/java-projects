package com.credai.dutyapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credai.dutyapi.entity.Employee;
import com.credai.dutyapi.repository.DutyRepository;

@Service
public class DutyServiceImpl implements DutyService{
	@Autowired
	private DutyRepository dutyRepository;
	
	@Override
	public Employee createEmployee(Employee emp) {
		Employee employee = dutyRepository.save(emp);
		return employee;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList = dutyRepository.findAll();
		return empList;
	}

	@Override
	public Employee getEmployee(long empId) {
		Employee employee = dutyRepository.findById(empId).get();
		return employee;
	}

	@Override
	public Employee updateEmployee(long empId, Employee emp) {
		emp.setEmpId(empId);
		Employee employee = dutyRepository.save(emp);
		return employee;
	}

	@Override
	public boolean deleteEmployee(long empId) {
		if(dutyRepository.findById(empId).isPresent()) {
			dutyRepository.deleteById(empId);
			return true;
		}
		return false;
	}

}
