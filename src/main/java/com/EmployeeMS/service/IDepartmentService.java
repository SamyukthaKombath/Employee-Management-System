package com.EmployeeMS.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.EmployeeMS.model.Departments;
import com.EmployeeMS.request.DepartmentRequestDto;
import com.EmployeeMS.response.DepartmentResponse;

public interface IDepartmentService {

	public Departments createDepartment(Departments dep);

	public Departments updateDepartment(Departments department);

	public Page<Departments> fetchAllDepartments(int offset, int pageSize);

	public DepartmentResponse deleteDepartment(long departmentId);

	public Page<Departments> findAllEmployeesInADepartment(int offset, int pageSize, String expand);

	public List<DepartmentRequestDto> getAllDept(int offset, int pageSize);

}
