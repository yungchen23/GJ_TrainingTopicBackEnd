package com.restbackend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "employee")
public class Employee {


	@Id
	private Integer employee_id;
	
	private String employee_name;
	private String employee_position;
	private String employee_jointime;
	private String employee_phone;
	private String employee_birth;
	private String employee_mail;
	
	
	@OneToOne   //定義Employee與LoginForEmployee之間是一對一的關係，//employee_id 和 login_id是一一對應的
	@JoinColumn(name="employee_id",referencedColumnName = "login_id")
	private LoginForEmployee loginForEmployee;
	
	
    public Employee() {
        super();
    }
	
	
	public Employee(
			Integer employee_id, String employee_name, String employee_position, String employee_jointime,
			String employee_phone, String employee_birth, String employee_mail
			) {
		super();
		this.employee_id = employee_id;
		this.employee_name = employee_name;
		this.employee_position = employee_position;
		this.employee_jointime = employee_jointime;
		this.employee_phone = employee_phone;
		this.employee_birth = employee_birth;
		this.employee_mail = employee_mail;
	}

	public Integer getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(Integer employee_id) {
		this.employee_id = employee_id;
	}

    public LoginForEmployee getLoginForEmployee() {
        return loginForEmployee;
    }

    public void setLoginForEmployee(LoginForEmployee loginForEmployee) {
        this.loginForEmployee = loginForEmployee;
    }
	
	public String getEmployee_name() {
		return employee_name;
	}

	public void setEmployee_name(String employee_name) {
		this.employee_name = employee_name;
	}

	public String getEmployee_position() {
		return employee_position;
	}

	public void setEmployee_position(String employee_position) {
		this.employee_position = employee_position;
	}

	public String getEmployee_jointime() {
		return employee_jointime;
	}

	public void setEmployee_jointime(String employee_jointime) {
		this.employee_jointime = employee_jointime;
	}

	public String getEmployee_phone() {
		return employee_phone;
	}

	public void setEmployee_phone(String employee_phone) {
		this.employee_phone = employee_phone;
	}

	public String getEmployee_birth() {
		return employee_birth;
	}

	public void setEmployee_birth(String employee_birth) {
		this.employee_birth = employee_birth;
	}

	public String getEmployee_mail() {
		return employee_mail;
	}

	public void setEmployee_mail(String employee_mail) {
		this.employee_mail = employee_mail;
	}


//	@Override
//	public String toString() {
//		return "Employee [employee_name=" + employee_name + ", employee_position=" + employee_position
//				+ ", employee_jointime=" + employee_jointime + ", employee_phone=" + employee_phone
//				+ ", employee_birth=" + employee_birth + ", employee_mail=" + employee_mail + ", loginForEmployee="
//				+ loginForEmployee + "]";
//	}

	@Override
	public String toString() {
		return "Employee [employee_id=" + employee_id + ", employee_name=" + employee_name + ", employee_position="
				+ employee_position + ", employee_jointime=" + employee_jointime + ", employee_phone=" + employee_phone
				+ ", employee_birth=" + employee_birth + ", employee_mail=" + employee_mail + "]";
	}
}
