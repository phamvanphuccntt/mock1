package com.itsol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="USER_INFO")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_USER_INFO")
	@SequenceGenerator(name = "SEQ_USER_INFO", sequenceName = "SEQ_USER_INFO", allocationSize = 1)
	@Column(name = "USER_ID", nullable = false)
	private int userId;
	
	@Column(name = "AVATAR")
	private String avatar;
	
	@Column(name = "SKYPE")
	private String skype;
	
	@Column(name = "FACEBOOK")
	private String facebook;
	
	@Column(name = "NATIVE_LAND")
	private String nativeLand;
	
	@Column(name = "EDUCATION")
	private String education;
	
	@Column(name = "SCHOOL")
	private String school;
	
	@Column(name = "GRADUATION_YEAR")
	private int graduationYear;
	
	@Column(name = "SKILL")
	private String skill;
	
	@Column(name = "WORK_EXPERIENCE")
	private String workExperience;
	
	@OneToOne
	@JoinColumn(name = "ACCOUNT_ID", unique = true)
	private Account account;

	public User(int userId, String avatar, String skype, String facebook, String nativeLand, String education,
			String school, int graduationYear, String skill, String workExperience, Account account) {
		super();
		this.userId = userId;
		this.avatar = avatar;
		this.skype = skype;
		this.facebook = facebook;
		this.nativeLand = nativeLand;
		this.education = education;
		this.school = school;
		this.graduationYear = graduationYear;
		this.skill = skill;
		this.workExperience = workExperience;
		this.account = account;
	}
	 
	public User() {
		// TODO Auto-generated constructor stub
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public String getSkype() {
		return skype;
	}

	public void setSkype(String skype) {
		this.skype = skype;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getNativeLand() {
		return nativeLand;
	}

	public void setNativeLand(String nativeLand) {
		this.nativeLand = nativeLand;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	public String getSchool() {
		return school;
	}

	public void setSchool(String school) {
		this.school = school;
	}

	public int getGraduationYear() {
		return graduationYear;
	}

	public void setGraduationYear(int graduationYear) {
		this.graduationYear = graduationYear;
	}

	public String getSkill() {
		return skill;
	}

	public void setSkill(String skill) {
		this.skill = skill;
	}

	public String getWorkExperience() {
		return workExperience;
	}

	public void setWorkExperience(String workExperience) {
		this.workExperience = workExperience;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}   
	
	   
}
