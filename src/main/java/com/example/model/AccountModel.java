package com.example.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 設定該Column 生成方式。 GenerationType.IDENTITY:資料庫維護。
	private Integer id;
	
	@Length(min = 1, max = 25, message ="使用者名稱不可少於1位或超過25位")
	@NotEmpty(message = "使用者名稱不可為空")
	//@UniqueElements(message = "使用者名稱已被註冊") // 只能用在List檢查傳入的一串資料有無重複，不是檢查資料庫內有無重複
	@Column(name = "username", unique = true)
	private String username;
	
	@Length(min = 4, max = 25, message ="帳號不可少於4位或超過25位")
	@NotEmpty(message = "帳號不可為空")
	//@UniqueElements(message = "此帳號已被註冊") // 只能用在List檢查傳入的一串資料有無重複，不是檢查資料庫內有無重複
	@Column(name = "account")
	private String account;
	
	//@Length(min = 4, max = 25, message ="密碼不可少於4位或超過25位") // 會卡BCryptPasswordEncoder，暫時拔掉
	@NotEmpty(message = "密碼不可為空")
	@Column(name = "password")
	private String password;

	@CreationTimestamp // 設定為建立時間，當實體被INSERT 時會預設值，不可用@CreatedDate，因為格式不同(java.util.date)。
	@Column(name = "create_time")
	private Date createTime;

	@Column(name = "permission")
	private Integer permission = 1;// 欄位為notnull，需從物件上給定預設值避免報錯。

}