package com.itsol.services;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.itsol.dao.AccountDAO;
import com.itsol.model.Account;
import com.itsol.model.Role;
import com.itsol.security.JwtTokenProvider;

@Service
public class AccountService {

	@Autowired
	private AccountDAO accountDAO;
	@Autowired
	private SendMailService senmail;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Autowired
	private JwtTokenProvider jwtTokenProvider;

	public String changePassword(String oldPassword, String newPassword, HttpServletRequest req) {
		Account account = whoami(req);
		if (passwordEncoder().matches(oldPassword, account.getPassword())) {
			account.setPassword(passwordEncoder().encode(newPassword));
			updateAccount(account.getUsername(), account);
			return "success";
		}
		return "password incorrect";
	}

	public Account getAnAccountByUsername(String username) {
		return accountDAO.getAnAccountByUsername(username);
	}

	public String login(String username, String password) {
		Account account = accountDAO.getAnAccountByUsername(username);
		if (account != null) {
			if (!account.isStatus()) {
				return "not activated";
			} else {
				if (passwordEncoder().matches(password, account.getPassword())) {
					return jwtTokenProvider.createToken(username);
				} else {
					return "fail";
				}
			}
		} else {
			return "not found";
		}
	}

	public String signup(Account account) {
		if (accountDAO.getAnAccountByUsername(account.getUsername()) != null) {
			return "duplicate username";
		} else if (accountDAO.getAnAccountByEmail(account.getEmail()) != null) {
			return "duplicate email";
		} else if (accountDAO.getAnAccountByPhone(account.getPhone()) != null) {
			return "duplicate phone";
		} else {
			String random = (new Random().nextInt(899999) + 10000) + "";
			account.setPassword(passwordEncoder().encode(random));
			account.setDateCreated(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
			account.setStatus(false);
			account.setRole(Role.CANDIDATE);
			String id = accountDAO.insertAccount(account) + "";
			senmail.senMail(account.getEmail(), random, account.getUsername());
			return id;
		}

	}

	public Account whoami(HttpServletRequest req) {
		return accountDAO.getAnAccountByUsername(jwtTokenProvider.getUsername(jwtTokenProvider.resolveToken(req)));
	}

	public Long countPage(int show) {
		return accountDAO.countPage(show);
	}

	public List<Account> getListAccount() {
		return accountDAO.getListAccount();
	}

	public List<Account> getListAccountAmount(int Amount) {
		return accountDAO.getListAccountAmount(Amount);
	}

	public Account getAnAccountById(int accountId) {
		return accountDAO.getAnAccountById(accountId);
	}

	public List<Account> getListPagenation(int MIN, int max) {
		return accountDAO.getListPagenation(MIN, max);
	}

	public String insertAccount(Account account) {
		account.setDateCreated(new SimpleDateFormat("dd/MM/yyyy HH:mm").format(new Date()));
		account.setPassword(passwordEncoder().encode(account.getPassword()));
		if (accountDAO.getAnAccountByUsername(account.getUsername()) != null) {
			return "duplicate username";
		} else if (accountDAO.getAnAccountByEmail(account.getEmail()) != null) {
			return "duplicate email";
		} else if (accountDAO.getAnAccountByPhone(account.getPhone()) != null) {
			return "duplicate phone";
		} else {
			return accountDAO.insertAccount(account) + "";
		}

	}

	public boolean deleteAccount(int accountId) {
		return accountDAO.deleteAccount(accountId);
	}

	public String updateAccount(String username, Account account) {
		Account acc = accountDAO.getAnAccountByUsername(username);
		if ((!acc.getEmail().equals(account.getEmail()))
				&& accountDAO.getAnAccountByEmail(account.getEmail()) != null) {
			return "duplicate email";
		} else if ((!acc.getPhone().equals(account.getPhone()))
				&& accountDAO.getAnAccountByPhone(account.getPhone()) != null) {
			return "duplicate phone";
		} else {
			return accountDAO.updateAccount(username, account);
		}
	}

	public boolean Activeaccount(String username) {
		return accountDAO.Activeaccount(username);
	}

}
