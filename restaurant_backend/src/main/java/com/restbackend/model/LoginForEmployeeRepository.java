package com.restbackend.model;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface LoginForEmployeeRepository extends JpaRepository<LoginForEmployee,Integer>{


	@Query("SELECT e FROM LoginForEmployee e WHERE e.login_account = ?1")
	LoginForEmployee findByLoginAccount(String loginAccount);
	
	
}
