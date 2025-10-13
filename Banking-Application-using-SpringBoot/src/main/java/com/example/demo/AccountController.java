package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Account;
import com.example.demo.service.AccountService;

@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;

	@PostMapping("/account/create")
	public Account createAccount(@RequestBody Account account) {
		return accountService.createAccount(account);
	}

	@GetMapping("/account/{id}")
	public ResponseEntity<Account> getAccount(@PathVariable Long id) {
		Account account = accountService.getAccount(id).orElse(null);
		if (account != null) {
			return ResponseEntity.ok().body(account);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/account/{id}/deposit")
	public ResponseEntity<Account> deposit(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		double amount = request.get("amount");
		Account account = accountService.deposit(id, amount);
		if (account != null) {
			return ResponseEntity.ok().body(account);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

	@PostMapping("/account/{id}/withdraw")
	public ResponseEntity<Account> withdraw(@PathVariable Long id, @RequestBody Map<String, Double> request) {
		double amount = request.get("amount");
		Account account = accountService.withdraw(id, amount);
		if (account != null) {
			return ResponseEntity.ok().body(account);
		} else {
			return ResponseEntity.notFound().build();
		}
	}

}
