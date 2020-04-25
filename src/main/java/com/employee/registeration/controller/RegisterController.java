package com.employee.registeration.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.employee.registeration.dto.EmployeeDTO;
import com.employee.registeration.dto.LoginDTO;
import com.employee.registeration.service.EmployeeService;

@RestController
@CrossOrigin

public class RegisterController {
	
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	
	
	@Bean
	public RestTemplate restTemplate() {
	    return new RestTemplate();
	}
	
	
	@Autowired
	EmployeeService employeeService;
	
	@Autowired
	RestTemplate restTemplate;
	
	@RequestMapping(value="/signup",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public int register(@RequestBody EmployeeDTO employeeDTO) {
		logger.info("SignUP request for Employee {} with EMPLOYEEID {}", employeeDTO.getEmployeeId());
		
		return employeeService.employee(employeeDTO);
		
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public String login(@RequestBody LoginDTO loginDTO ) throws Exception {
		logger.info("login Req request for Employee ");
		
		boolean resp= employeeService.employeeLogin(loginDTO);
		System.out.println(resp);
		
		String posResp="Attendance Marked";
		String negResp="Attendance not Marked!! please try again!!";
		if(resp) {
			ResponseEntity<Integer> attendanceId=restTemplate.postForEntity("http://localhost:3333/availablity/"+loginDTO.getEmployeeId(), loginDTO, int.class);
			if(attendanceId!=null) {
				return 	posResp	;	}
		}
		return negResp;
		
	}
	
@RequestMapping(value="/update/{empId}",method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)


	public void updateEmployeeDetails(@RequestBody EmployeeDTO employeeDTO,@PathVariable int empId) throws Exception {
		
		logger.info("UPDATE Req request for Employee " +empId);
		
		employeeService.employeeUpdate(employeeDTO,empId);
		
	}

	
	@RequestMapping(value="/remove/{empId}",method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public void removeEmployee(@PathVariable int empId ) throws Exception {
		
		logger.info("deletion Req request for Employee " +empId);
		
		employeeService.removeEmployee(empId);
		
		
		
	}
	
}
