package com.EmployeeMS.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.EmployeeMS.model.Employees;
import com.EmployeeMS.repository.IEmployeeRepository;
import com.EmployeeMS.request.EmployeeMSRequestDto;
import com.EmployeeMS.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService {

	@Autowired
	private IEmployeeRepository dao;

	@Override
	public Employees createEmployee(Employees emp) {
		return dao.save(emp);
	}

	@Override
	public Employees updateEmployee(Employees emp) {
		Optional<Employees> employees = dao.findById(emp.getId());
		if (employees.isPresent()) {
			Employees e = employees.get();
			e.setName(emp.getName());
			e.setDOB(emp.getDOB());
			e.setSalary(emp.getSalary());
			e.setDepartmentName(emp.getDepartmentName());
			e.setRole(emp.getRole());
			e.setJoiningDate(emp.getJoiningDate());
			e.setYearlyBonusPercentage(e.getYearlyBonusPercentage());
			e.setReportingManager(emp.getReportingManager());
			return dao.save(e);
		} else {
			return null;
		}
	}

	@Override
	public Employees updateDepartment(EmployeeMSRequestDto dto) {
		Employees searchEmployee = dao.searchId(dto.getId());
		if (searchEmployee != null) {

			dao.updateDepartment(dto.getDepartmentName(), searchEmployee.getId());
		}
		return searchEmployee;
	}

	@Override
	public Page<Employees> getAllEmployees(int offset, int pageSize) {
		return dao.findAll(PageRequest.of(offset, pageSize));
	}

}
