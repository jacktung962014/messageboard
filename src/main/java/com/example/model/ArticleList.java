package com.example.model;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class ArticleList {
	@Id
	private Integer id;
	private String topic;
	private String username;
	private Date create_time;
}