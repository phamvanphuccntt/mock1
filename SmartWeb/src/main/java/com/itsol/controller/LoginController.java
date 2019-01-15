package com.itsol.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.model.Account;
import com.itsol.services.AccountService;

@RestController
public class LoginController {
	
	@Autowired
	private AccountService accountService;

	@RequestMapping(value = "login", method = RequestMethod.POST)
	public String login(@RequestParam String username, @RequestParam String password) {
		return accountService.login(username,password);
	}
	
	@RequestMapping(value = "signup", method = RequestMethod.POST)
	public String signup(@RequestBody Account account) {
		return accountService.signup(account);
	}
	
	@RequestMapping(value = "whoami", method = RequestMethod.GET)
	public Account whoami(HttpServletRequest req) {
		return accountService.whoami(req);
	}
	
	@RequestMapping(value = "change-password", method = RequestMethod.POST)
	public String changePassword(@RequestParam String oldPassword, @RequestParam String newPassword, HttpServletRequest req) {
		return accountService.changePassword(oldPassword, newPassword, req);
	}

}
