package com.EmployeeMS.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
@Entity
@Table(name = "employeedb")
public class Employees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private String name;
	@Column(name = "dob")
	private Date dOB;
	private double salary;
	@Column(name = "department")
	private String departmentName;
	private String address;
	private String role;
	@Column(name = "joining_date")
	private Date joiningDate;
	@Column(name = "yearly_bonus_percentage")
	private double yearlyBonusPercentage;
	@Column(name = "reporting_manager")
	private String reportingManager;

	@JsonIgnore
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "department_id")
	private Departments departments;

}
