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
@Table(name = "JOB_DETAIL")
public class JobDetail {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_JOB_DETAIL")
	@SequenceGenerator(name = "SEQ_JOB_DETAIL", sequenceName = "SEQ_JOB_DETAIL", allocationSize = 1)
	@Column(name = "JOB_ID")
	private int jobId;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_JOB_ID")
	private CategoryJob categoryJob;

	@Column(name = "JOB_NAME", nullable = false)
	private String jobName;

	@Column(name = "SALARY", nullable = false)
	private int salary;

	@Column(name = "THUMBNAIL", nullable = false)
	private String thumbnail;

	@Column(name = "DESCRIPTION", nullable = false)
	private String description;

	@Column(name = "CONTENT", nullable = false)
	private String content;

	@Column(name = "ADDRESS", nullable = false)
	private String address;

	@Column(name = "INTEREST", nullable = false)
	private String interest;

	@Column(name = "JOB_REQUEST", nullable = false)
	private String jobRequest;

	@Column(name = "EXPIRATION_DATE", nullable = false)
	private String expirationDate;

	@Column(name = "DATE_SUBMITTED", nullable = false)
	private String dateSubmitted;

	@Column(name = "WORKING_FORM", nullable = false)
	private WorkingForm workingForm;

	@Column(name = "HOT", nullable = false)
	private boolean hot;

	@Column(name = "STATUS", nullable = false)
	private boolean status;

	@ManyToOne
	@JoinColumn(name = "CITY_ID")
	private City city;

	public JobDetail() {
		super();
	}

	public JobDetail(int jobId, CategoryJob categoryJob, String jobName, int salary, String thumbnail,
			String description, String content, String address, String interest, String jobRequest,
			String expirationDate, String dateSubmitted, WorkingForm workingForm, boolean hot, boolean status,
			City city) {
		super();
		this.jobId = jobId;
		this.categoryJob = categoryJob;
		this.jobName = jobName;
		this.salary = salary;
		this.thumbnail = thumbnail;
		this.description = description;
		this.content = content;
		this.address = address;
		this.interest = interest;
		this.jobRequest = jobRequest;
		this.expirationDate = expirationDate;
		this.dateSubmitted = dateSubmitted;
		this.workingForm = workingForm;
		this.hot = hot;
		this.status = status;
		this.city = city;
	}

	public int getJobId() {
		return jobId;
	}

	public void setJobId(int jobId) {
		this.jobId = jobId;
	}

	public CategoryJob getCategoryJob() {
		return categoryJob;
	}

	public void setCategoryJob(CategoryJob categoryJob) {
		this.categoryJob = categoryJob;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getJobRequest() {
		return jobRequest;
	}

	public void setJobRequest(String jobRequest) {
		this.jobRequest = jobRequest;
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getDateSubmitted() {
		return dateSubmitted;
	}

	public void setDateSubmitted(String dateSubmitted) {
		this.dateSubmitted = dateSubmitted;
	}

	public WorkingForm getWorkingForm() {
		return workingForm;
	}

	public void setWorkingForm(WorkingForm workingForm) {
		this.workingForm = workingForm;
	}

	public boolean isHot() {
		return hot;
	}

	public void setHot(boolean hot) {
		this.hot = hot;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

}
