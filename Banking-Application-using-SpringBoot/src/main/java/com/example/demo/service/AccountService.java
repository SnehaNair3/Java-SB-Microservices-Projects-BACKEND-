package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.entity.Account;
import com.example.demo.repository.AccountRepository;

@Service
public class AccountService {

	@Autowired
	private AccountRepository accountRepository;

	public Account createAccount(Account account) {
		return accountRepository.save(account);
	}

	public Optional<Account> getAccount(Long id) {
		return accountRepository.findById(id);
	}

	public Account deposit(Long id, double amount) {
		Account account = getAccount(id).orElse(null);
		if (account != null) {
			account.setBalance(account.getBalance() + amount);
			return accountRepository.save(account);
		} else {
			throw new RuntimeException("Account not found.");
		}
	}

	public Account withdraw(Long id, double amount) {
		Account account = getAccount(id).orElse(null);
		if (account != null) {
			account.setBalance(account.getBalance() - amount);
			return accountRepository.save(account);
		} else {
			throw new RuntimeException("Account not found.");
		}
	}
}
