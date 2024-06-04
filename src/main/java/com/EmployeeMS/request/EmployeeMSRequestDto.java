package com.EmployeeMS.request;

import javax.persistence.Column;

import lombok.Data;

@Data
public class EmployeeMSRequestDto {

	private long id;
	@Column(name = "department")
	private String departmentName;
}
