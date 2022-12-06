package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDao;
import com.example.model.AccountModel;

@Service
public class AccountService {
	@Autowired
	AccountDao accountDao;
	public void deleteAccount(Integer deleteAccount){
		accountDao.deleteById(deleteAccount);
	}
	
	public AccountModel addAccount(AccountModel addAccount) {
		return accountDao.save(addAccount);
	}
	
	public AccountModel getAccountById(Integer id) {
		return accountDao.findById(id).get();
	}
}
