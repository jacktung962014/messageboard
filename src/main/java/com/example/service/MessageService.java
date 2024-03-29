package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.CommentDao;
import com.example.dao.MessageDao;
import com.example.model.Comment;
import com.example.model.Entity.MessageModel;

@Service
public class MessageService {
	@Autowired
	MessageDao messageDao;

	// find方法
	public MessageModel findMessage(Integer id) {
		return messageDao.findById(id).get();
	}

	// save方法
	public MessageModel saveMessage(MessageModel saveMessage) {
		return messageDao.save(saveMessage);
	}

	// delete方法
	public void deleteMessage(Integer id) {
		messageDao.deleteById(id);
	}

	// deleteBy方法
	public void deleteMessageByBoardID(Integer id) {
		messageDao.deleteByboardID(id);
	}

	@Autowired
	// findByID方法
	CommentDao commentDao;

	public List<Comment> getComments(Integer id) {
		return commentDao.findByID(id);
	}

}
