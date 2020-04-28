package com.employee.registeration.dto;

import java.time.LocalDate;


import com.employee.registeration.entity.Employee;

public class EmployeeDTO {
	
	private String emailId;
	private int employeeId;
	private String password;
	private LocalDate dob;
	private String name;
	
	
	public int getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public LocalDate getDob() {
		return dob;
	}
	
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public void setDob(LocalDate dob) {
		this.dob = dob;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public Employee employeeEntity(EmployeeDTO empDTO) {
		
		Employee employeeEntity= new Employee();
		
		employeeEntity.setEmailId(empDTO.getEmailId());
		employeeEntity.setName(empDTO.getName());
		employeeEntity.setDob(empDTO.getDob());
		employeeEntity.setPassword(empDTO.getPassword());
		//employeeEntity.setPassword(new BCryptPasswordEncoder().encode(this.getPassword()));
		return employeeEntity;
		
		
	}
	
public EmployeeDTO employeeDetailsModel(Employee emp) {
		
		EmployeeDTO employeeModel= new EmployeeDTO();
		employeeModel.setEmployeeId(emp.getEmployeeId());
		employeeModel.setEmailId(emp.getEmailId());
		employeeModel.setName(emp.getName());
		employeeModel.setDob(emp.getDob());
		employeeModel.setPassword(emp.getPassword());
		return employeeModel;
		
		
	}
	
	
}
