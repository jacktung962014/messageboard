package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.SearchMessageboardDao;
import com.example.model.MessageboardModel;

@Service
public class SearchMessageboardService {
	@Autowired
	SearchMessageboardDao searchMessageboardDao;

	public List<MessageboardModel> getMessageboard() {

		return searchMessageboardDao.findAll();

	}
	public MessageboardModel getMessageboardById(Integer id) {
		
		return searchMessageboardDao.findById(id).get();
	}
}
