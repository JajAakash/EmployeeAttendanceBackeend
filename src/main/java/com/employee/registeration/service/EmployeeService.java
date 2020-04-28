package com.employee.registeration.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.employee.registeration.dto.EmployeeDTO;
import com.employee.registeration.dto.LoginDTO;
import com.employee.registeration.entity.Employee;
import com.employee.registeration.repository.EmployeeRepository;

@Service
@Transactional(readOnly = false)

public class EmployeeService {
	
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	
	public int employee(EmployeeDTO employeeDTO ) {
		Employee employee = employeeDTO.employeeEntity(employeeDTO);
		Employee empRespo = employeeRepository.save(employee);
		
		
		return empRespo.getEmployeeId() ;
	}
	
	
	
	@SuppressWarnings("unused")
	public boolean employeeLogin(LoginDTO loginDTO ) {
		
		if(! employeeRepository.findById(loginDTO.getEmployeeId()).isPresent()) {
			//throw new Exception("YOU ARE NOT AUTHORIZED PERSON!!  kindly REGISTER !!");
			return false;
		}
		
		Optional<Employee> loginDetails=employeeRepository.findById(loginDTO.getEmployeeId());
		
		if(loginDetails.get().getPassword().equals(loginDTO.getPassword())) {
			
			return true;
		}
		return false;
						
	}
	

	public void removeEmployee(int empId) {
		
		employeeRepository.deleteById(empId);
	}



	public int employeeUpdate(EmployeeDTO empDTO, int empId) {
		// TODO Auto-generated method stub
		
		Employee e = new EmployeeDTO().employeeEntity(empDTO);
		//e.setName(empDTO.getName());
		return employeeRepository.employeeUpdate(e.getName(), e.getDob(), e.getPassword(), empId);
		
		
	}
	
	public EmployeeDTO employeeDetails(int empId) {
		Optional<Employee> details=employeeRepository.findById(empId);
		return new EmployeeDTO().employeeDetailsModel(details.get());
		
		
	}
	
}
