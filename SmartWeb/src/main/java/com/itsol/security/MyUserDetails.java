package com.itsol.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.itsol.dao.AccountDAO;
import com.itsol.model.Account;

@Service
public class MyUserDetails implements UserDetailsService {

	@Autowired
	private AccountDAO accountDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Account account = accountDAO.getAnAccountByUsername(username);
		if (account == null)
			return null;
		return User.withUsername(username).password(account.getPassword()).roles(account.getRole().toString()).accountExpired(false).accountLocked(false)
				.credentialsExpired(false).disabled(false).build();
	}

}
