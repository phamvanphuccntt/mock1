package com.itsol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT")
public class Account {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_ACCOUNT")
	@SequenceGenerator(name = "SEQ_ACCOUNT", sequenceName = "SEQ_ACCOUNT", allocationSize = 1)
	@Column(name = "ACCOUNT_ID")
	private int accountId;
	@Column(name = "username", nullable = false, unique = true)
	private String username;
	@Column(name = "PASSWORD", nullable = false)
	private String password;
	@Column(name = "EMAIL", nullable = false, unique = true)
	private String email;
	@Column(name = "FULL_NAME", nullable = false)
	private String fullname;
	@Column(name = "ROLE", nullable = false)
	private Role role;
	@Column(name = "STATUS", nullable = false)
	private boolean status;
	@Column(name = "DATE_CREATED", nullable = false)
	private String dateCreated;
	@Column(name = "LAST_ACCESS")
	private String lastAccess;
	@Column(name = "PHONE", nullable = false, unique = true)
	private String phone;
	@Column(name = "NOTES")
	private String notes;

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFullname() {
		return fullname;
	}

	public void setFullname(String fullname) {
		this.fullname = fullname;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(String dateCreated) {
		this.dateCreated = dateCreated;
	}

	public String getLastAccess() {
		return lastAccess;
	}

	public void setLastAccess(String lastAccess) {
		this.lastAccess = lastAccess;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public Account(int accountId, String username, String password, String email, String fullname, Role role,
			boolean status, String dateCreated, String lastAccess, String phone, String notes) {
		super();
		this.accountId = accountId;
		this.username = username;
		this.password = password;
		this.email = email;
		this.fullname = fullname;
		this.role = role;
		this.status = status;
		this.dateCreated = dateCreated;
		this.lastAccess = lastAccess;
		this.phone = phone;
		this.notes = notes;
	}

	public Account() {
		// TODO Auto-generated constructor stub
	}

}
