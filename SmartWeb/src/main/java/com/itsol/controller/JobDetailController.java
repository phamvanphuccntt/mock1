package com.itsol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.model.JobDetail;
import com.itsol.services.JobDetailService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Job Controller")
public class JobDetailController {
	@Autowired
	public JobDetailService jobDetailService;

	@RequestMapping(value = "/job", method = RequestMethod.GET)
	public List<JobDetail> getAllJob() {
		return jobDetailService.getAllJob();
	}

	/*@RequestMapping(value = "/jobC/{categoryJobId}", method = RequestMethod.GET)
	public List<JobDetail> getJobByCategory(@PathVariable("categoryJobId") int categoryJobId) {
		return jobDetailService.getJobDetailByJobCategoryID(categoryJobId);
	}*/

	@RequestMapping(value = "/job/hot", method = RequestMethod.GET)
	public List<JobDetail> getJobHot() {
		return jobDetailService.getJobHot();
	}

	@RequestMapping(value = "/job/{jobId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_MANAGER')")
	public boolean updateJobDetail(@PathVariable("jobId") int jobId, @RequestBody JobDetail jobDetail) {
		return jobDetailService.updateJobDetail(jobId, jobDetail);
	}

	@RequestMapping(value = "/job/{jobId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_MANAGER')")
	public boolean deleteJobDetail(@PathVariable("jobId") int jobId) {
		return jobDetailService.deleteJobDetail(jobId);
	}

	@RequestMapping(value = "/job", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_MANAGER')")
	public int insertJobDetail(@RequestBody JobDetail jobDetail) {
		return jobDetailService.insertJobDetail(jobDetail);
	}

	@RequestMapping(value = "/job/page-size/{show}", method = RequestMethod.GET)
	public long countPageSize(@PathVariable("show") long show) {
		return jobDetailService.getPageSize(show);
	}

	@RequestMapping(value = "/job/{page}/{show}", method = RequestMethod.GET)
	public List<JobDetail> getListJobByPage(@PathVariable("show") int show, @PathVariable("page") int page) {
		return jobDetailService.getListJobByPage(show, page);
	}
	
	@RequestMapping(value = "/job/{jobId}", method = RequestMethod.GET)
	public List<JobDetail> getJobDetailById(@PathVariable("jobId") int jobId){
		return jobDetailService.getJobDetailById(jobId);
	}
}
