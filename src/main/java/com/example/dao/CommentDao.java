package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Entity.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {
	@Query(nativeQuery = true, value = "SELECT m.id, m.board_id, a.username, m.member_message, m.create_time"
			+ " FROM message m"
			+ " INNER JOIN account a ON m.user_id = a.id"
			+ " Where m.board_id = :name"
			+ " ORDER BY m.create_time;")
	List<Comment> findByID(@Param("name") Integer id);
}
