package com.itsol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.model.CategoryNews;
import com.itsol.services.CategoryNewsService;

@RestController
public class CategoryNewsController {

	@Autowired
	private CategoryNewsService categoryNewsService;
	
	@RequestMapping(value = "category-news", method = RequestMethod.GET)
	public List<CategoryNews> getAllCategoryNews() {
		return categoryNewsService.getListCategoryNews();
	}
	
	/*@RequestMapping(value = "category-news/{categoryNewsId}", method = RequestMethod.GET)
	public CategoryNews getCategoryNewsById(@PathVariable("categoryNewsId") int categoryNewsId) {
		return categoryNewsService.getAnCategoryNewsById(categoryNewsId);
	}*/
	
	@RequestMapping(value = "category-news", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MARKETING') or hasRole('ROLE_MANAGER')")
	public int insertCategoryNews(@RequestBody CategoryNews categoryNews) {
		return categoryNewsService.insertCategoryNews(categoryNews);
	}
	
	@RequestMapping(value = "category-news/{categoryNewsId}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_MARKETING') or hasRole('ROLE_MANAGER')")
	public boolean deleteCategoryNews(@PathVariable("categoryNewsId") int categoryNewsId) {
		return categoryNewsService.deleteCategoryNews(categoryNewsId);
	}
	
	@RequestMapping(value = "category-news/{categoryNewsId}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_MARKETING') or hasRole('ROLE_MANAGER')")
	public boolean updateCategoryNews(@PathVariable("categoryNewsId") int categoryNewsId, @RequestBody CategoryNews categoryNews) {
		return categoryNewsService.updateCategoryNewsId(categoryNewsId, categoryNews);
	}
	
}
