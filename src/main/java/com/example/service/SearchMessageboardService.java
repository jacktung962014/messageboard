package com.example.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.ArticleListDao;
import com.example.dao.SearchMessageboardDao;
import com.example.model.ArticleList;
import com.example.model.Entity.MessageboardModel;

@Service
public class SearchMessageboardService {
	@Autowired
	SearchMessageboardDao searchMessageboardDao;

	// findAll方法
	public List<MessageboardModel> getMessageboard() {
		return searchMessageboardDao.findAll();
	}

	// findById方法
	public MessageboardModel getMessageboardById(Integer id) {
		return searchMessageboardDao.findById(id).get();
	}

	// save方法
	public MessageboardModel saveMessageboard(MessageboardModel messageboardModel) {
		return searchMessageboardDao.save(messageboardModel);
	}

	// delete方法
	public void deleteMessageboardById(Integer id) {
		searchMessageboardDao.deleteById(id);
	}

	@Autowired
	ArticleListDao articleListDao;

	// 取得ArticleList方法
	public List<ArticleList> getArticleList() {
		return articleListDao.getArticleList();
	}
}
