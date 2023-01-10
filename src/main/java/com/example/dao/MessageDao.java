package com.example.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Entity.MessageModel;

@Repository
public interface MessageDao extends JpaRepository<MessageModel ,Integer> {
	@Transactional// 避免報錯
	void deleteByboardID(Integer id);
}
