package com.restbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "loginforemployee")
public class LoginForEmployee {

	@Id
	private Integer login_id;
	private String login_account;
	private String login_password;
	private Boolean employee_state;

	public LoginForEmployee() {
		super();
		// TODO Auto-generated constructor stub
	}

	public LoginForEmployee(Integer login_id, String login_account, String login_password, Boolean employee_state) {
		super();
		this.login_id = login_id;
		this.login_account = login_account;
		this.login_password = login_password;
		this.employee_state = employee_state;
	}

	public Integer getLogin_id() {
		return login_id;
	}

	public void setLogin_id(Integer login_id) {
		this.login_id = login_id;
	}

	public String getLogin_account() {
		return login_account;
	}

	public void setLogin_account(String login_account) {
		this.login_account = login_account;
	}

	public String getLogin_password() {
		return login_password;
	}

	public void setLogin_password(String login_password) {
		this.login_password = login_password;
	}

	public Boolean getEmployee_state() {
		return employee_state;
	}

	public void setEmployee_state(Boolean employee_state) {
		this.employee_state = employee_state;
	}

	@Override
	public String toString() {
		return "LoginForEmployee [login_id=" + login_id + ", login_account=" + login_account + ", login_password="
				+ login_password + ", employee_state=" + employee_state + "]";
	}

}
