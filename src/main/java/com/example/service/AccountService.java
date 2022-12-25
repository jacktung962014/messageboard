package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDao;
import com.example.model.AccountModel;

@Service
public class AccountService {
	@Autowired
	AccountDao accountDao;

	// delete方法
	public void deleteAccount(Integer deleteAccount) {
		accountDao.deleteById(deleteAccount);
	}

	// save方法
	public AccountModel addAccount(AccountModel addAccount) {
		return accountDao.save(addAccount);
	}

	// findById方法
	public AccountModel getAccountById(Integer id) {
		return accountDao.findById(id).get();
	}

	// findByAccount方法
	public AccountModel getIdentityByAccount(String account) {
		return accountDao.findByAccount(account);
	}

	// checkExistsUsername方法
	public Integer checkExistsUsername(String account) {
		return accountDao.checkIfUsernameExists(account);
	}
	
	// checkExistsAccount方法
		public Integer checkExistsAccount(String account) {
			return accountDao.checkIfAccountExists(account);
		}
}
