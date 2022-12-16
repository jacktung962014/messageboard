package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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
	
}