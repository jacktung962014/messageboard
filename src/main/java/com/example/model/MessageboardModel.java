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

}