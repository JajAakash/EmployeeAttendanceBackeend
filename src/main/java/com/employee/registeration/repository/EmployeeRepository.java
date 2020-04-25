package com.employee.registeration.repository;

import java.time.LocalDate;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.employee.registeration.entity.Employee;

public interface EmployeeRepository extends CrudRepository <Employee, Integer>  {
	
	Employee save(Employee employee);
	
	@Modifying
	@Query("update Employee emp set emp.name = ?1,emp.dob = ?2, emp.password = ?3 where emp.EmployeeId = ?4")
	public int employeeUpdate(String name,LocalDate dob,String password, int id);

}
