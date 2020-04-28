package com.employee.registeration.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "employee")


@SequenceGenerator(name="seq", initialValue = 1000, allocationSize = 1)
public class Employee {

	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq")
	@Id
	@Column(nullable = false)
	private int EmployeeId;
	
	@Column(nullable = false)
	private String emailId;
	
	@Column(nullable = false)
	private String password;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private LocalDate dob;

	public int getEmployeeId() {
		return EmployeeId;
	}
	

	public void setEmployeeId(int employeeId) {
		EmployeeId = employeeId;
	}




	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}


	public String getEmailId() {
		return emailId;
	}


	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	
	
	
	
}
