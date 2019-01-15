package com.itsol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.itsol.model.NewsDetail;
import com.itsol.services.NewsDetailService;

import io.swagger.annotations.Api;

@RestController
@Api(tags = "News Controller")	
public class NewsDetailController {
	@Autowired
	public NewsDetailService newsDetailService;
	
	@RequestMapping(value = "news/hot", method = RequestMethod.GET)
	public List<NewsDetail> getNewsHot() {
		return newsDetailService.getNewsHot();
	}
	
	@RequestMapping(value = "news", method = RequestMethod.GET)
	public List<NewsDetail> getAllNews() {
		return newsDetailService.getAllNews();
	}
	
	@RequestMapping(value = "news/{newsID}", method = RequestMethod.GET)
	public List<NewsDetail> getNewsById(@PathVariable("newsID") int newsID) {
		return  newsDetailService.getNewsById(newsID);
	}
	
	@RequestMapping(value = "news", method = RequestMethod.POST)
	@PreAuthorize("hasRole('ROLE_MARKETING') or hasRole('ROLE_MANAGER')")
	public int insertNewsDetail(@RequestBody NewsDetail newsDetail) {
		return newsDetailService.insertNewsDetail(newsDetail);
	}
	
	@RequestMapping(value = "news/{newsID}", method = RequestMethod.DELETE)
	@PreAuthorize("hasRole('ROLE_MARKETING') or hasRole('ROLE_MANAGER')")
	public boolean deleteNewsDetail(@PathVariable("newsID") int newsID) {
		return newsDetailService.deleteNewsDetail(newsID);
	}
	
	@RequestMapping(value = "news/{newsID}", method = RequestMethod.PUT)
	@PreAuthorize("hasRole('ROLE_MARKETING') or hasRole('ROLE_MANAGER')")
	public boolean updateNewsDetail(@PathVariable("newsID") int newsID, @RequestBody NewsDetail newsDetail) {
		return newsDetailService.updateNewsDeTail(newsID, newsDetail);
	}
	
	@RequestMapping(value = "news/page-size/{show}", method = RequestMethod.GET)
	public long getPageSize(@PathVariable("show") long show) {
		return newsDetailService.getPageSize(show);
	}
	
	@RequestMapping(value = "news/{page}/{show}", method = RequestMethod.GET)
	public List<NewsDetail> getListNewsByPage(@PathVariable("show") int show, @PathVariable("page") int page) {
		return newsDetailService.getListNewsByPage(show, page);
	}
	
	@RequestMapping(value = "news/list-by-category/{categoryNewsId}", method = RequestMethod.GET)
	public List<NewsDetail> getNewsByCategory(@PathVariable("categoryNewsId") int categoryNewsId) {
		return newsDetailService.getNewsByCategory(categoryNewsId);
	}
	
}
