package com.example.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.Comment;

@Repository
public interface CommentDao extends JpaRepository<Comment, Integer> {
	@Query(nativeQuery = true, value = "SELECT m.id, m.board_id, a.username, m.member_message, m.create_time"
			+ " FROM message m"
			+ " INNER JOIN account a ON m.user_id = a.id"
			+ " Where m.board_id = :name"// :name是指下面的Integer id透過@Param("name")這個key傳入SQL語法
			+ " ORDER BY m.id;")
	List<Comment> findByID(@Param("name") Integer id);// @Param("name")是key，Integer id是傳入的value
}
