package com.credai.empapi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.credai.empapi.entity.Employee;
import com.credai.empapi.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService{
	@Autowired
	private EmployeeRepository employeeRepository;

	@Override
	public Employee createEmployee(Employee employee) {
		Employee employ = employeeRepository.save(employee);
		return employ;
	}

	@Override
	public List<Employee> getAllEmployee() {
		List<Employee> empList = employeeRepository.findAll();
		return empList;
	}

	@Override
	public Employee getEmployee(long empId) {
		Employee employee = employeeRepository.findById(empId).get();
		return employee;
	}

	@Override
	public Employee updateEmployee(long empId, Employee emp) {
		emp.setEmpId(empId);
		Employee employee = employeeRepository.save(emp);
		return null;
	}

	@Override
	public boolean deleteEmployee(long empId) {
		if(employeeRepository.existsById(empId)) {
			employeeRepository.deleteById(empId);
			return true;
		}
		return false;
	}

}
