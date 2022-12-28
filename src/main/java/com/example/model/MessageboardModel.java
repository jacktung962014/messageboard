package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "messageboard")
public class MessageboardModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 設定該Column 生成方式。 GenerationType.IDENTITY:資料庫維護。
	private Integer id;
	
	@Length(min = 4, max = 30, message ="標題不可少於4個字或超過30個字")
	@NotEmpty(message = "標題不可為空")
	@Column(name = "topic")
	private String topic;
	
	@Length(min = 10, max = 1000, message ="貼文不可少於10個字或超過1000個字")
	@NotEmpty(message = "貼文不可為空")
	@Column(name = "member_text")
	private String memberText;
	
	@Column(name = "user_id")
	private Integer userID;
	
	@CreationTimestamp // 設定為建立時間，當實體被INSERT 時會預設值，不可用@CreatedDate，因為格式不同(java.util.date)。
	@Column(name = "create_time")
	private Date createTime;

}