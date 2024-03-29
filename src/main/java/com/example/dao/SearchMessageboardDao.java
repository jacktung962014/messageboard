package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.Entity.MessageboardModel;

@Repository
public interface SearchMessageboardDao extends JpaRepository<MessageboardModel, Integer> {
}
