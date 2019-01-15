package com.itsol.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORY_JOB")
public class CategoryJob {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORY_JOB")
	@SequenceGenerator(name = "SEQ_CATEGORY_JOB", sequenceName = "SEQ_CATEGORY_JOB", allocationSize = 1)
	@Column(name = "CATEGORY_JOB_ID")
	private int categoryJobId;

	@Column(name = "CATEGORY_NAME", nullable = false)
	private String categoryName;

	@Column(name = "STATUS", nullable = false)
	private boolean status;

	public CategoryJob() {
		super();
	}

	public CategoryJob(int categoryJobId, String categoryName, boolean status) {
		super();
		this.categoryJobId = categoryJobId;
		this.categoryName = categoryName;
		this.status = status;
	}

	public int getCategoryJobId() {
		return categoryJobId;
	}

	public void setCategoryJobId(int categoryJobId) {
		this.categoryJobId = categoryJobId;
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
