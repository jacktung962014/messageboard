package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "message")
public class MessageModel {
	@Id
	private int id;
	@Column(name = "user_id")
	private int userID;
	@Column(name = "board_id")
	private int boardID;
	@Column(name = "member_message")
	private String memberMessage;
	@Column(name = "create_time")
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

	public String getMemberMessage() {
		return memberMessage;
	}

	public void setMemberMessage(String memberMessage) {
		this.memberMessage = memberMessage;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("MessageModel [id=");
		builder.append(id);
		builder.append(", userID=");
		builder.append(userID);
		builder.append(", boardID=");
		builder.append(boardID);
		builder.append(", memberMessage=");
		builder.append(memberMessage);
		builder.append(", createTime=");
		builder.append(createTime);
		builder.append("]");
		return builder.toString();
	}

}