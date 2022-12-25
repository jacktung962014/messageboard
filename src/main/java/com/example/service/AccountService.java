package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDao;
import com.example.model.AccountModel;

@Service
public class AccountService {
	@Autowired
	AccountDao accountDao;
	
	//delete方法
	public void deleteAccount(Integer deleteAccount){
		accountDao.deleteById(deleteAccount);
	}
	
	//save方法
	public AccountModel addAccount(AccountModel addAccount) {
		return accountDao.save(addAccount);
	}
	
	//findById方法
	public AccountModel getAccountById(Integer id) {
		return accountDao.findById(id).get();
	}
	
	//findByAccount方法
	public AccountModel getIdentityByAccount(String account) {
		return accountDao.findByAccount(account);
	}
}
