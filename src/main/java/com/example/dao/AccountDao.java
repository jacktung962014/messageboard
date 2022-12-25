package com.example.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.model.AccountModel;

@Repository
public interface AccountDao extends JpaRepository<AccountModel, Integer> {

	AccountModel findByAccount(String account);

}
