package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "messageboard")
public class MessageboardModel {
	@Id
	private Integer id;
	@Column(name = "topic")
	private String topic;
	@Column(name = "member_text")
	private String memberText;
	@Column(name = "user_id")
	private Integer userID;
	@Column(name = "create_time")
	private Date createTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTopic() {
		return topic;
	}

	public void setTopic(String topic) {
		this.topic = topic;
	}

	public String getMemberText() {
		return memberText;
	}

	public void setMemberText(String memberText) {
		this.memberText = memberText;
	}

	public Integer getUserID() {
		return userID;
	}

	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}