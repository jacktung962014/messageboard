package com.example.dao;


import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.model.AccountModel;

@Repository
public interface AccountDao extends JpaRepository<AccountModel, Integer> {
	

	
}
