package com.sp.www.Service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.sp.www.ResourceNotFoundException.ResourceNotFoundException;
import com.sp.www.model.Employee;
import com.sp.www.repository.EmployeeRepository;

@Service

public class EmployeeService 
{
	@Autowired
	EmployeeRepository employeeRepository;

	public List<Employee> getAllEmployees()
	{
		return employeeRepository.findAll();
	}
	public Employee addEmployee( Employee employee)
	{
		return employeeRepository.save(employee);
	}
	public Employee getEmployeeById(Long id)
	{
		return employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Id Not Found"));
	}
    public  ResponseEntity<Employee> updateEmployee( Long id, Employee employee)
    {
    	Employee emp= employeeRepository .findById(id).orElseThrow(()-> new ResourceNotFoundException("employee does not exit"));
    	emp.setId(employee.getId());
    	emp.setFirstName(employee.getFirstName());
    	emp.setLastName(employee.getLastName());
    	emp.setEmail(employee.getEmail());
    	 Employee updateEmp=employeeRepository.save(emp);
    	 
    	 return ResponseEntity.ok(updateEmp);
    	
    }
    public ResponseEntity<HttpStatus> deleteEmployee( Long id)
	{
		Employee emp= employeeRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("ID IS NOT FOUND"));
		
		employeeRepository.delete(emp);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}



}
