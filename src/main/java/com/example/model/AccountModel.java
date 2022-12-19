package com.example.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "account")
public class AccountModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)//設定該Column 生成方式。 GenerationType.IDENTITY:資料庫維護。
	private Integer id;
	@Column(name = "username")
	private String username;
	@Column(name = "account")
	private String account;
	@Column(name = "password")
	private String password;
	
	@CreationTimestamp //設定為建立時間，當實體被INSERT 時會預設值，不可用@CreatedDate，因為格式不同(java.util.date)。
	@Column(name = "create_time")
	private Date createTime;
	
	@Column(name = "permission")
	private Integer permission = 1;//欄位為notnull，需從物件上給定預設值避免報錯。
	
}