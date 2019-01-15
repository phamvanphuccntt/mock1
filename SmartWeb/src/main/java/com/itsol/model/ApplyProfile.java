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
@Table(name = "APPLY_PROFILE")
public class ApplyProfile {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_APPLY_PROFILE")
	@SequenceGenerator(name = "SEQ_APPLY_PROFILE", sequenceName = "SEQ_APPLY_PROFILE", allocationSize = 1)
	@Column(name = "APPLY_PROFILE_ID")
	private int applyProfleId;
	@ManyToOne
	@JoinColumn(name = "ACCOUNT_ID")
	private Account account;
	@ManyToOne
	@JoinColumn(name = "JOB_ID")
	private JobDetail jobDetail;
	@Column(name = "CV", nullable = false)
	private String cv;
	@Column(name = "NOTES", nullable = true)
	private String notes;
	@Column(name = "STATUS", nullable = false)
	private boolean status;

	public ApplyProfile(int applyProfleId, Account account, JobDetail jobDetail, String cv, String notes,
			boolean status) {
		super();
		this.applyProfleId = applyProfleId;
		this.account = account;
		this.jobDetail = jobDetail;
		this.cv = cv;
		this.notes = notes;
		this.status = status;
	}

	public ApplyProfile() {
		super();
	}

	public int getApplyProfleId() {
		return applyProfleId;
	}

	public void setApplyProfleId(int applyProfleId) {
		this.applyProfleId = applyProfleId;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public JobDetail getJobDetail() {
		return jobDetail;
	}

	public void setJobDetail(JobDetail jobDetail) {
		this.jobDetail = jobDetail;
	}

	public String getCv() {
		return cv;
	}

	public void setCv(String cv) {
		this.cv = cv;
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

}
