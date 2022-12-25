package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "message")
public class MessageModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 設定該Column 生成方式。 GenerationType.IDENTITY:資料庫維護。
	private int id;
	
	@Column(name = "user_id")
	private int userID;
	
	@Column(name = "board_id")
	private int boardID;
	
	@Column(name = "member_message")
	private String memberMessage;
	
	@CreationTimestamp // 設定為建立時間，當實體被INSERT 時會預設值，不可用@CreatedDate，因為格式不同(java.util.date)。
	@Column(name = "create_time")
	private Date createTime;

}