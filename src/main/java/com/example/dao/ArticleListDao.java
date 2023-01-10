package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.ArticleList;

@Repository
public interface ArticleListDao extends JpaRepository<ArticleList, Integer> {
	
	@Query(nativeQuery = true, value = "SELECT mb.id, mb.topic, a.username, mb.create_Time"
			+ " FROM messageboard mb"
			+ " INNER JOIN account a ON mb.user_id = a.id"
			+ " ORDER BY mb.create_Time DESC")
	List<ArticleList> getArticleList();
}
