package com.EmployeeMS.request;

import java.util.Date;

import lombok.Data;

@Data
public class DepartmentRequestDto {
	private long id;
	private String name;
	private Date creationDate;
	private String departmentHead;
}
