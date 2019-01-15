package com.itsol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY_NEWS")
public class CategoryNews {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORY_NEWS")
	@SequenceGenerator(name = "SEQ_CATEGORY_NEWS", sequenceName = "SEQ_CATEGORY_NEWS", allocationSize = 1)
	@Column(name = "CATEGORY_NEWS_ID")
	private int categoryNewsId;

	@Column(name = "CATEGORY_NAME", nullable = false)
	private String categoryName;

	@Column(name = "STATUS", nullable = false)
	private boolean status;

	public CategoryNews() {
		super();
	}

	public CategoryNews(int categoryNewsId, String categoryName, boolean status) {
		super();
		this.categoryNewsId = categoryNewsId;
		this.categoryName = categoryName;
		this.status = status;
	}

	public int getCategoryNewsId() {
		return categoryNewsId;
	}

	public void setCategoryNewsId(int categoryNewsId) {
		this.categoryNewsId = categoryNewsId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
