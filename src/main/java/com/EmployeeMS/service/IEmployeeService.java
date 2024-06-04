package com.EmployeeMS.service;

import org.springframework.data.domain.Page;

import com.EmployeeMS.model.Employees;
import com.EmployeeMS.request.EmployeeMSRequestDto;

public interface IEmployeeService {

	public Employees createEmployee(Employees emp);

	public Employees updateEmployee(Employees emp);

	public Page<Employees> getAllEmployees(int offset, int pageSize);

	public Employees updateDepartment(EmployeeMSRequestDto dto);

}
