package com.itsol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "news_detail")
public class NewsDetail {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_NEWS_DETAIL")
	@SequenceGenerator(name = "SEQ_NEWS_DETAIL", sequenceName = "SEQ_NEWS_DETAIL", allocationSize = 1)
	@Column(name = "NEWS_ID", nullable = false)
	private int newsID;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_NEWS_ID", nullable = false)
	private CategoryNews categoryNews;

	@Column(name = "TITLE", nullable = false)
	private String title;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "DATE_SUBMITTED", nullable = false)
	private String dateSubmitted;

	@Column(name = "THUMBNAIL", nullable = false)
	private String thumbnail;

	@Column(name = "STATUS", nullable = false)
	private boolean status;

	@Column(name = "HOT", nullable = false)
	private boolean hot;

	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID", nullable = false)
	private Account account;

	public NewsDetail() {
		super();
	}

	public NewsDetail(int newsID, CategoryNews categoryNews, String title, String description, String content,
			String dateSubmitted, String thumbnail, boolean status, boolean hot, Account account) {
		super();
		this.newsID = newsID;
		this.categoryNews = categoryNews;
		this.title = title;
		this.description = description;
		this.content = content;
		this.dateSubmitted = dateSubmitted;
		this.thumbnail = thumbnail;
		this.status = status;
		this.hot = hot;
		this.account = account;
	}

	public int getNewsID() {
		return newsID;
	}

	public void setNewsID(int newsID) {
		this.newsID = newsID;
	}

	public CategoryNews getCategoryNews() {
		return categoryNews;
	}

	public void setCategoryNews(CategoryNews categoryNews) {
		this.categoryNews = categoryNews;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	
}
