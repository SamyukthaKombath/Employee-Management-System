package com.EmployeeMS.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.EmployeeMS.model.Departments;
import com.EmployeeMS.model.Employees;
import com.EmployeeMS.repository.IDepartmentsRepository;
import com.EmployeeMS.repository.IEmployeeRepository;
import com.EmployeeMS.request.DepartmentRequestDto;
import com.EmployeeMS.response.DepartmentResponse;
import com.EmployeeMS.service.IDepartmentService;

@Service
public class DepartmentServiceImpl implements IDepartmentService {

	@Autowired
	private IDepartmentsRepository dao;

	@Autowired
	private IEmployeeRepository dao1;

	@Override
	public Departments createDepartment(Departments dep) {
		return dao.save(dep);
	}

	@Override
	public Page<Departments> fetchAllDepartments(int offset, int pageSize) {
		return dao.findAll(PageRequest.of(offset, pageSize));
	}

	@Override
	public Departments updateDepartment(Departments department) {
		Optional<Departments> dep = dao.findById(department.getId());
		if (dep.isPresent()) {
			Departments d = dep.get();
			d.setName(department.getName());
			d.setCreationDate(department.getCreationDate());
			d.setDepartmentHead(department.getDepartmentHead());
			return dao.save(d);
		} else {
			return null;
		}

	}

	@Override
	public Page<Departments> findAllEmployeesInADepartment(int offset, int pageSize, String expand) {
		if (expand.equalsIgnoreCase("employee")) {
			return dao.findAll(PageRequest.of(offset, pageSize));
		} else {
			return null;
		}
	}

	@Override
	public DepartmentResponse deleteDepartment(long departmentId) {
		DepartmentResponse response = new DepartmentResponse();
		Optional<Departments> dep = dao.findById(departmentId);
		if (!dep.isPresent()) {
			response.setStatusCode("404");
			response.setStatusMessage("Department not found");
			return response;
		}
		List<Employees> employees = dao1.findByDepartmentsId(departmentId);
		if (!employees.isEmpty()) {
			response.setStatusCode("400");
			response.setStatusMessage("Cannot delete department with employees");
			return response;
		}
		dao.deleteById(departmentId);
		response.setStatusCode("200");
		response.setStatusMessage("Department deleted successfully");
		return response;
	}

	@Override
	public List<DepartmentRequestDto> getAllDept(int offset, int pageSize) {
		List<Departments> dep = dao.findAll();
		List<DepartmentRequestDto> dto = new ArrayList<DepartmentRequestDto>();
		for (Departments department : dep) {
			DepartmentRequestDto dtos = new DepartmentRequestDto();
			BeanUtils.copyProperties(department, dtos);
			dto.add(dtos);
		}
		return dto;
	}

}
