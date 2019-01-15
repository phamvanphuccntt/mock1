package com.itsol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsol.dao.JobDetailDAO;
import com.itsol.model.JobDetail;

@Service
public class JobDetailService {
	@Autowired
	JobDetailDAO jobDetailDAO;

	public List<JobDetail> getAllJob() {
		return jobDetailDAO.getAllJob();
	}

	/*public List<JobDetail> getJobDetailByJobCategoryID(int categoryJobId) {
		return jobDetailDAO.getJobByCategoryId(categoryJobId);
	}*/

	public List<JobDetail> getJobHot() {
		return jobDetailDAO.getJobHot();
	}
	
	public boolean updateJobDetail(int jobId, JobDetail jobDetail) {
		return jobDetailDAO.updateJobDetail(jobId, jobDetail);
	}
	
	public boolean deleteJobDetail(int jobId) {
		return jobDetailDAO.deleteJobDetail(jobId);
	}
	
	public int insertJobDetail(JobDetail jobDetail) {
		return jobDetailDAO.insertJobDetail(jobDetail);
	}
	
	public long getPageSize(long show) {
		return jobDetailDAO.getPageSize(show);
	}
	
	public List<JobDetail> getListJobByPage(int show, int page) {
		return jobDetailDAO.getListJobByPage(show, page);
	}
	
	public List<JobDetail> getJobDetailById(int jobId){
		return jobDetailDAO.getJobById(jobId);
	}
}
