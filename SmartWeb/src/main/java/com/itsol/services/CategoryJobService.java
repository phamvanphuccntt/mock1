package com.itsol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsol.dao.CategoryJobDAO;
import com.itsol.model.CategoryJob;

@Service
public class CategoryJobService {

	@Autowired
	CategoryJobDAO categoryJobDAO;
	
	public List<CategoryJob> getListCategoryJob() {
		return categoryJobDAO.getListCategoryJob();
	}
	
	public int insertCategoryJob(CategoryJob categoryJob) {
		return categoryJobDAO.insertCategoryJob(categoryJob);
	}
	
	public boolean deleteCategoryJob(int categoryJobId) {
		return categoryJobDAO.deleteCategoryJob(categoryJobId);
	}
	
	public boolean updateCategoryJob(int categoryJobId, CategoryJob categoryJob) {
		return categoryJobDAO.updateCategoryJob(categoryJobId, categoryJob);
	}
	
}
