package com.EmployeeMS.response;

import java.util.List;

import org.springframework.data.domain.Page;

import com.EmployeeMS.model.Employees;
import com.EmployeeMS.request.EmployeeMSRequestDtoAlt;

import lombok.Data;



@Data
public class EmployeeResponse {

private Employees data1;
private String statusCode;
private String statusMessage;
private List<Employees> dataList1;
private Page<Employees> dataList2;
private List<EmployeeMSRequestDtoAlt> dataList3;
}
