package com.itsol.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.itsol.model.User;
import com.itsol.services.UserService;
@RestController
public class UserController {
	@Autowired
	UserService userService;

	/*@RequestMapping(value = "/user", method = RequestMethod.GET)
	public List<User> getListUser() {
		return userService.getListUser();
	}*/

	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public int insertUser(@RequestBody User user) {
		return userService.insertUser(user);
	}

	/*@RequestMapping(value = "/user/{userId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_CANDIDATE') or hasRole('ROLE_MANAGER')")
	public boolean deleteUser(@PathVariable("userId") int userId) {
		return userService.deleteUser(userId);
	}*/

	@RequestMapping(value = "/user/{userId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_CANDIDATE') or hasRole('ROLE_MANAGER')")
	public boolean updateUser(@PathVariable("userId") int userId,
			@RequestBody User user) {
		return userService.updateUser(user, userId);
	}
	@RequestMapping(value = "/user/{userId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_CANDIDATE') or hasRole('ROLE_MANAGER')")
	public User getUserId(@PathVariable("userId") int userId) {
		return userService.getUserId(userId);
	}
}
