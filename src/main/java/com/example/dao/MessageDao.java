package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.model.MessageModel;

@Repository
public interface MessageDao extends JpaRepository<MessageModel ,Integer> {
}
