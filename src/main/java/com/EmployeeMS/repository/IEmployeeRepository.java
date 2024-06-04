package com.EmployeeMS.repository;





import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.EmployeeMS.model.Employees;



public interface IEmployeeRepository extends JpaRepository<Employees, Long> {
	
	
	
@Query(value="select * from employeedb where id=:id",nativeQuery = true)	
public Employees searchId(@Param("id") long id);	
	
@Transactional
@Modifying
@Query(value="update employeedb set department=:departmentName where id=:id ",nativeQuery = true)	
public void updateDepartment(@Param("departmentName") String departmentName,@Param("id") long id);	

public List<Employees> findByDepartmentsId(Long departmentId);

}
