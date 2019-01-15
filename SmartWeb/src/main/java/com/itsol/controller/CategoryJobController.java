package com.itsol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.model.CategoryJob;
import com.itsol.services.CategoryJobService;

@RestController
public class CategoryJobController {

	@Autowired
	CategoryJobService categoryJobService;

	@RequestMapping(value = "/category-job", method = RequestMethod.GET)
	public List<CategoryJob> getListCategoryJob() {
		return categoryJobService.getListCategoryJob();
	}

	@RequestMapping(value = "/category-job", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_MANAGER')")
	public int insertCategoryJob(@RequestBody CategoryJob categoryJob) {
		return categoryJobService.insertCategoryJob(categoryJob);
	}

	@RequestMapping(value = "/category-job/{categoryJobId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_MANAGER')")
	public boolean deleteCategoryJob(@PathVariable("categoryJobId") int categoryJobId) {
		return categoryJobService.deleteCategoryJob(categoryJobId);
	}

	@RequestMapping(value = "/category-job/{categoryJobId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_MANAGER')")
	public boolean updateCategoryJob(@PathVariable("categoryJobId") int categoryJobId,
			@RequestBody CategoryJob categoryJob) {
		return categoryJobService.updateCategoryJob(categoryJobId, categoryJob);
	}

}
