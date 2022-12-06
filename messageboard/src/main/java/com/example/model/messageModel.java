package com.example.model;

import java.sql.Clob;
import java.sql.Date;

import org.springframework.stereotype.Component;

@Component
public class messageModel {

	private int id;
	private int userID;
	private int boardID;
	private Clob comment;
	private Date createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}

	public int getBoardID() {
		return boardID;
	}
	
	public void setBoardID(int boardID) {
		this.boardID = boardID;
	}
	
	public Clob getComment() {
		return comment;
	}
	
	public void setComment(Clob comment) {
		this.comment = comment;
	}

	public Date getcreateTime() {
		return createTime;
	}

	public void setcreateTime(Date createTime) {
		this.createTime = createTime;
	}

}