package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "account")
public class AccountModel {

	@Id
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "account")
	private String account;
	@Column(name = "password")
	private String password;
	@Column(name = "create_time")
	private Date createTime;
	@Column(name = "permission")
	private Integer permission;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getusername() {
		return username;
	}

	public void setusername(String username) {
		this.username = username;
	}

	public String getaccount() {
		return account;
	}

	public void setaccount(String account) {
		this.account = account;
	}

	public String getpassword() {
		return password;
	}

	public void setpassword(String password) {
		this.password = password;
	}

	public Date getcreateTime() {
		return createTime;
	}

	public void setcreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getPermission() {
		return permission;
	}

	public void setPermission(Integer permission) {
		this.permission = permission;
	}

}