package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.MessageDao;
import com.example.model.MessageModel;

@Service
public class MessageService {
	@Autowired 
	MessageDao messageDao;
	public MessageModel saveMessage(MessageModel saveMessage){
		return messageDao.save(saveMessage);
	}
		
}
