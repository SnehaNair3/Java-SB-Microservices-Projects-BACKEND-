package com.example.banking_app.service.impl;

import org.springframework.stereotype.Service;

import com.example.banking_app.dto.AccountDto;
import com.example.banking_app.entity.Account;
import com.example.banking_app.mapper.AccountMapper;
import com.example.banking_app.repository.AccountRepository;
import com.example.banking_app.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {

	private AccountRepository accountRepository;

	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}

	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedAccount = accountRepository.save(account);
		return AccountMapper.mapToAccountDto(savedAccount);
	}

	@Override
	public AccountDto getAccountById(Long id) {
		accountRepository.findById(id).orElseThrow()
		return null;
	}

}
