package com.example.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Comment {
	@Id
	private Integer id;
	private Integer board_id;
	private String username;
	private String memberMessage;
	private Date createTime;
}
