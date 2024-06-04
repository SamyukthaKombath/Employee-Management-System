package com.EmployeeMS.response;





import java.util.List;

import org.springframework.data.domain.Page;

import com.EmployeeMS.model.Departments;


import lombok.Data;



@Data
public class DepartmentResponse {
 
	
	
private Departments data;
private String statusCode;
private String statusMessage;
private List<Departments> dataList;
private Page<Departments> dataList1;

}
