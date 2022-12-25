package com.example.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CommentDao;
import com.example.dao.MessageDao;
import com.example.model.MessageModel;
import com.example.model.Entity.Comment;

@Service
public class MessageService {
	@Autowired 
	MessageDao messageDao;
	//save方法
	public MessageModel saveMessage(MessageModel saveMessage){
		return messageDao.save(saveMessage);
	}
	
	@Autowired
	//findByID方法
	CommentDao commentDao;
	public List<Comment> getComments(Integer id){
		return commentDao.findByID(id);
	}
		
}
