package com.restbackend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

//	Employee findByLoginForEmployee_login_account(String loginAccount);
	
//	@Query("SELECT e FROM #{#entityName} e WHERE e.loginForEmployee.login_account = ?1")
	@Query("SELECT e FROM Employee e WHERE e.loginForEmployee.login_account = ?1")
	Employee findByLoginAccount(String loginAccount);
}
