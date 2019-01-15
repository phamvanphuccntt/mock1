package com.itsol.model;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="setting")
public class Setting {

	@Id
	@Column(name = "SETTING_ID", nullable = false)
	private int settingId;
	
	@Column(name = "COMPANY_NAME", nullable = false)
	private String companyName;
	
	@Column(name = "LOGO", nullable = false)
	private String logo;
	
	@Column(name = "ADDRESS", nullable = false)
	private String address;
	
	@Column(name = "PHONE", nullable = false)
	private String phone;
	
	@Column(name = "FACEBOOK")
	private String facebook;
	
	@Column(name = "TWITTER")
	private String twitter;
	
	@Column(name = "HEADER", nullable = false)
	private String header;
	
	@Column(name = "FOOTER", nullable = false)
	private String footer;
	
	@Column(name = "VIDEO")
	private String video;
	
	public Setting() {
		// TODO Auto-generated constructor stub
	}

	public Setting(int settingId, String companyName, String logo, String address, String phone, String facebook,
			String twitter, String header, String footer, String video) {
		super();
		this.settingId = settingId;
		this.companyName = companyName;
		this.logo = logo;
		this.address = address;
		this.phone = phone;
		this.facebook = facebook;
		this.twitter = twitter;
		this.header = header;
		this.footer = footer;
		this.video = video;
	}

	public int getSettingId() {
		return settingId;
	}

	public void setSettingId(int settingId) {
		this.settingId = settingId;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getTwitter() {
		return twitter;
	}

	public void setTwitter(String twitter) {
		this.twitter = twitter;
	}

	public String getHeader() {
		return header;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getFooter() {
		return footer;
	}

	public void setFooter(String footer) {
		this.footer = footer;
	}

	public String getVideo() {
		return video;
	}

	public void setVideo(String video) {
		this.video = video;
	}
	
}
