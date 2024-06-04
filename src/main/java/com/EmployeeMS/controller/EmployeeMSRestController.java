package com.EmployeeMS.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.EmployeeMS.model.Departments;
import com.EmployeeMS.model.Employees;
import com.EmployeeMS.request.DepartmentRequestDto;
import com.EmployeeMS.request.EmployeeMSRequestDto;
import com.EmployeeMS.request.EmployeeMSRequestDtoAlt;
import com.EmployeeMS.response.DepartmentResponse;
import com.EmployeeMS.response.EmployeeResponse;
import com.EmployeeMS.service.IDepartmentService;
import com.EmployeeMS.service.IEmployeeService;

@RestController
public class EmployeeMSRestController {

@Autowired
private IDepartmentService service;	

@Autowired
private IEmployeeService empService;
	


@PostMapping("/createDepartment")
public DepartmentResponse addDepartment(@RequestBody Departments d) {
	Departments department=service.createDepartment(d);
	DepartmentResponse response= new DepartmentResponse();
	if(department!=null) {
		response.setStatusMessage("New Department added");
		response.setStatusCode("200");
		response.setData(department);
	}
	else {
		response.setStatusMessage("No Departments added");
		response.setStatusCode("404");
	}
	          return response;
}


@PostMapping("/createEmployee")
public EmployeeResponse addEmployee(@RequestBody Employees e) {
	Employees employee=empService.createEmployee(e);
	EmployeeResponse response= new EmployeeResponse();
	if(employee!=null) {
		response.setStatusMessage("New Employee added");
		response.setStatusCode("200");
		response.setData1(employee);
	}
	else {
		response.setStatusMessage("No Employees added");
		response.setStatusCode("404");
	}
	          return response;
}


@PutMapping("/updateEmployee")
public EmployeeResponse updateEmployee(@RequestBody Employees emp) {
Employees employee = empService.updateEmployee(emp);
EmployeeResponse response = new EmployeeResponse();
if(employee!=null) {
	response.setStatusMessage("Employee details updated");
	response.setStatusCode("200");
	response.setData1(employee);
	
}
else {
	response.setStatusMessage("Employee not found");
	response.setStatusCode("404");
}
return response;
}

@PutMapping("/updateEmployeeDepartment")
public EmployeeResponse updateEmployeeDepartmentName(@RequestBody EmployeeMSRequestDto dto) {
	Employees e = empService.updateDepartment(dto);
	EmployeeResponse response = new EmployeeResponse();
	if (e != null) {
		response.setStatusMessage("Department name updated");
		response.setStatusCode("200");
		response.setData1(e);
	} else {
		response.setStatusMessage("Employee not found");
		response.setStatusCode("404");
	}
	return response;
}

@PutMapping("/updateDepartment")
public DepartmentResponse updateDepartment(@RequestBody Departments dep) {
Departments department = service.updateDepartment(dep);
DepartmentResponse response = new DepartmentResponse();
if(department!=null) {
	response.setStatusMessage("Department details updated");
	response.setStatusCode("200");
	response.setData(department);
	
}
else {
	response.setStatusMessage("Department not found");
	response.setStatusCode("404");
}
return response;
}

@GetMapping("/getAllEmployees/{offset}/{pageSize}")
public EmployeeResponse getAllEmployees(@PathVariable int offset,@PathVariable int pageSize) {
	Page<Employees> employees = empService.getAllEmployees(offset, pageSize);
	EmployeeResponse response = new EmployeeResponse();
	if(employees.getSize()>0) {
		response.setStatusMessage("List of Departments Found");
		response.setStatusCode("200");
		response.setDataList2(employees);
	}
	else {
		response.setStatusMessage(" List not Found");
		response.setStatusCode("404");
	}
	return response;
}

@GetMapping("/listOfDepartments/{offset}/{pagesize}")
public List<DepartmentRequestDto> findAllDepts(@PathVariable int page, @PathVariable int pagesize) {
	return service.getAllDept(page, pagesize);
}



@GetMapping("/getAllEmployeeNameAndId/{offset}/{pagesize}")
public List<EmployeeMSRequestDtoAlt> getEmployeeNameAndId(@RequestParam(value = "lookup", defaultValue = "false") boolean lookup,@PathVariable int offset,@PathVariable int pagesize) {
    if (lookup) {
        List<EmployeeMSRequestDtoAlt> dto = new ArrayList<>();
        Page<Employees> emp = empService.getAllEmployees(offset, pagesize);
        for (Employees employee : emp) {
        	EmployeeMSRequestDtoAlt employeeRequestDtoAlt = new EmployeeMSRequestDtoAlt();
        	employeeRequestDtoAlt.setId(employee.getId());
        	employeeRequestDtoAlt.setName(employee.getName());
            dto.add(employeeRequestDtoAlt);
        }
        return dto;
    } else {
        return new ArrayList<>(); 
    }
}




@GetMapping("/getAllEmployeesInADepartment/{offset}/{pageSize}")
public DepartmentResponse getAllEmployeeInADepartment(@PathVariable int offset, @PathVariable int pageSize,@RequestParam("expand") String expand) {
DepartmentResponse response = new DepartmentResponse();
Page<Departments> departments = service.findAllEmployeesInADepartment(offset,pageSize,expand);
if(departments!=null) {
	response.setStatusMessage("List of Departments found");
    response.setStatusCode("200");
    response.setDataList1(departments);
}
else {
	 response.setStatusMessage("List not Found");
     response.setStatusCode("404");
}
return response;
}

@DeleteMapping("/deleteDepartment/{departmentId}")
public DepartmentResponse deleteDepartment(@PathVariable long departmentId) {
    return service.deleteDepartment(departmentId);
}

}







