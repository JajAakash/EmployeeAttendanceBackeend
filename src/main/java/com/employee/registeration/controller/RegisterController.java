package com.employee.registeration.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
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
		logger.info("SignUP request for Employee {} with EMPLOYEEID {}");
		int id=employeeService.employee(employeeDTO);
		return id; 
		
	}
	
	@RequestMapping(value="/login",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public boolean login(@RequestBody LoginDTO loginDTO ) {
		logger.info("login Req request for Employee ");
		
		boolean resp= employeeService.employeeLogin(loginDTO);
		if(resp) {
			ResponseEntity<Integer> attendanceId=restTemplate.postForEntity("http://localhost:3333/availablity/"+loginDTO.getEmployeeId(), loginDTO, int.class);
			if(attendanceId!=null) {
				return 	true	;	}
		}
		return false;
		
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
	
	@RequestMapping(value="/employeedata/{empId}",method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	
	public List<Object> getDetails(@PathVariable int empId ) {
		logger.info("fetching DETAILS Req request for Employee ");
		List<Object> attendanceDetails=restTemplate.getForObject("http://localhost:3333/attendance/details/"+empId,List.class);
		EmployeeDTO employee=  employeeService.employeeDetails(empId);
		attendanceDetails.add(employee);
		return attendanceDetails;
		}
			
	}
	
