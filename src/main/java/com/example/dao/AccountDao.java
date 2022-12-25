package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.model.AccountModel;

@Repository
public interface AccountDao extends JpaRepository<AccountModel, Integer> {

	@Query(nativeQuery = true, value = "SELECT EXISTS(SELECT username FROM account a WHERE a.username = :name)")
	Integer checkIfUsernameExists(@Param("name") String username);// @Param("name")是key，String username是傳入的value
	// :name是指上面的String username透過@Param("name")這個key傳入SQL語法
	// SELECT EXISTS這句是指查詢資料庫中是否存在我們指定要找的值。
	@Query(nativeQuery = true, value = "SELECT EXISTS(SELECT account FROM account a WHERE a.account = :name)")
	Integer checkIfAccountExists(@Param("name") String account);
	
	
	AccountModel findByAccount(String account);

}
