package com.itsol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.model.ApplyProfile;
import com.itsol.services.ApplyProfileService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "Apply-Profile")
public class ApplyProfileController {
	@Autowired
	public ApplyProfileService applyProfileService;
	
	@RequestMapping(value = "/ungtuyen", method = RequestMethod.POST)
	public int insertProfile(@RequestBody ApplyProfile applyProfile) {
		return applyProfileService.insertProfile(applyProfile);
	}
	
	@RequestMapping(value = "/apply", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_HR')")
	public List<ApplyProfile> getListApplyProfile() {
		return applyProfileService.getListApplyProfile();
	}
	
	@RequestMapping(value = "/apply/{applyId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_HR')")
	public boolean updateApplyProfile(@PathVariable("applyId") int applyProfleId, @RequestBody ApplyProfile applyProfile) {
		return applyProfileService.updateApplyProfile(applyProfleId, applyProfile);
	}
	
	@RequestMapping(value = "/apply/page-size/{show}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_HR')")
	public long getPageSize(@PathVariable long show) {
		return applyProfileService.getPageSize(show);
	}
	
	@RequestMapping(value = "/apply/{page}/{show}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_HR')")
	public List<ApplyProfile> getListJobByPage(@PathVariable int show, @PathVariable int page) {
		return applyProfileService.getListJobByPage(show, page);
	}
	

	@RequestMapping(value = "/apply/{accountId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_HR')")
	public List<ApplyProfile> getListApplyProfiles(@PathVariable("accountId") int accountId){
		return applyProfileService.getListApplyProfiles(accountId);
	}
	@RequestMapping(value = "/apply/{applyId}", method = RequestMethod.GET)
	@PreAuthorize("hasRole('ROLE_MANAGER') or hasRole('ROLE_HR')")
	public ApplyProfile getApplyProfileById(@PathVariable("applyId") int applyProfleId) {
		return applyProfileService.getApplyProfileById(applyProfleId);

	}
}
