package com.example.model;

import java.sql.Clob;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class message_boardModel {

	private int id;
	private String topic;
	private Clob menber_text;
	private String userID;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}
	
	public void setTopic(String topic) {
		this.topic = topic;
	}
	
	public Clob getMenber_text() {
		return menber_text;
	}
	
	public void setMenber_text(Clob menber_text) {
		this.menber_text = menber_text;
	}
	
	public String getUserID() {
		return userID;
	}
	
	public void setUserID(String userID) {
		this.userID = userID;
	}

	public Date getcreateTime() {
		return createTime;
	}

	public void setcreateTime(Date createTime) {
		this.createTime = createTime;
	}

}