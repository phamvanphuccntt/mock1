package com.itsol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsol.dao.ApplyProfileDAO;
import com.itsol.model.ApplyProfile;

@Service
public class ApplyProfileService {
	@Autowired
	public ApplyProfileDAO applyProfileDAO;
	
	public int insertProfile(ApplyProfile applyProfile) {
		return applyProfileDAO.insertProfile(applyProfile);
	}
	
	public List<ApplyProfile> getListApplyProfile() {
		return applyProfileDAO.getListApplyProfile();
	}
	
	public boolean updateApplyProfile(int applyProfleId, ApplyProfile applyProfile) {
		return applyProfileDAO.updateApplyProfile(applyProfleId, applyProfile);
	}
	
	public long getPageSize(long show) {
		return applyProfileDAO.getPageSize(show);
	}
	
	public List<ApplyProfile> getListJobByPage(int show, int page) {
		return applyProfileDAO.getListJobByPage(show, page);
	}

	public List<ApplyProfile> getListApplyProfiles(int accountId){
		return applyProfileDAO.getListApplyProfiles(accountId);
	}
	public ApplyProfile getApplyProfileById(int id) {
		return applyProfileDAO.getApplyProfile(id);

	}
}
