package com.itsol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.model.Account;
import com.itsol.services.AccountService;
//@CrossOrigin(origins="http://localhost:8010")
@RestController
public class AccountController {

	@Autowired
	private AccountService accountService;
	
	@RequestMapping(value = "account", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public List<Account> getListAccount() {
		return accountService.getListAccount();
	}
	@RequestMapping(value = "account/amount/{SL}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public List<Account> getListAccountSL(@PathVariable("SL") int SL) {
		return accountService.getListAccountAmount(SL);
	}
	
	/*@RequestMapping(value = "account/{accountId}", method = RequestMethod.GET)
	public Account getAnAccountById(@PathVariable("accountId") int accountId) {
		return accountService.getAnAccountById(accountId);
	}*/
	
	@RequestMapping(value = "account/{username}", method = RequestMethod.GET)
	public Account getAnAccountByUsername(@PathVariable("username") String username) {
		return accountService.getAnAccountByUsername(username);
	}
	
	@RequestMapping(value = "account/page/{show}",method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public Long countAcount(@PathVariable("show") int show){
		return accountService.countPage(show);
		
	}
	@RequestMapping(value = "account/pagination/{first}/{end}",method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public List<Account> getListAcountPagenation(@PathVariable("first") int first , @PathVariable("end") int end){
		return accountService.getListPagenation(first,end);
		
	}
	
	@RequestMapping(value = "account", method = RequestMethod.POST)
//	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public String insertAccount(@RequestBody Account account) {
		return accountService.insertAccount(account);
	}
	
	@RequestMapping(value = "account/{accountId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_MANAGER')")
	public boolean deleteAccount(@PathVariable("accountId") int accountId) {
		return accountService.deleteAccount(accountId);
	}
	
	@RequestMapping(value = "account/{username}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_CANDIDATE')")
	public String updateAccount(@PathVariable("username") String username, @RequestBody Account account) {
		return accountService.updateAccount(username, account);
	}
	@RequestMapping(value = "account/active/{username}", method = RequestMethod.GET)

	public String activeAccount(@PathVariable("username") String username) {
		if( accountService.Activeaccount(username)) {
			return "<h1>Kích hoạt thành công</h1> <a  href=\"http://localhost:8010/MockProject_Team1/SmartWebFrontEnd/WebContent/#!/login\">Quay lại Login</a>";
		}else {
			return"<h1>kích tài khoản hoạt thất bại</h1>";
		}
	}
	
}
