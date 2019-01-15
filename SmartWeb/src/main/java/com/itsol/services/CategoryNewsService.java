package com.itsol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsol.dao.CategoryNewsDAO;
import com.itsol.model.CategoryNews;

@Service
public class CategoryNewsService {

	@Autowired
	private CategoryNewsDAO categoryNewsDAO;
	
	public List<CategoryNews> getListCategoryNews() {
		return categoryNewsDAO.getListCategoryNews();
		
	}
	
	/*public CategoryNews getAnCategoryNewsById(int categoryNewsId) {
		return categoryNewsDAO.getAnCategoryNewsById(categoryNewsId); 
	}*/
	
	public int insertCategoryNews(CategoryNews categoryNews) {
		return categoryNewsDAO.insertCategoryNews(categoryNews);
	}
	
	public boolean deleteCategoryNews(int categoryNewsId) {
		return categoryNewsDAO.deleteCategoryNews(categoryNewsId);
	}
	
	public boolean updateCategoryNewsId(int categoryNewsId, CategoryNews categoryNews) {
		return categoryNewsDAO.updateCategoryNews(categoryNewsId, categoryNews);
	}
}
