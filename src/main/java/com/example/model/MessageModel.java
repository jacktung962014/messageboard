package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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