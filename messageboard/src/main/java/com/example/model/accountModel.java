package com.example.model;

import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class accountModel {
	
	 private int id;
	  private String username;
	  private String account;
	  private String password;
	  private Date createTime;
	  
	  public int getId() {
		return id;
	  }
	  
	  public void setId(int id) {
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
	  
	  

}