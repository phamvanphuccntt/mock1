package com.itsol.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.itsol.dao.NewsDetailDAO;
import com.itsol.model.NewsDetail;

@Service
public class NewsDetailService {
	@Autowired
	public NewsDetailDAO newsDetailDAO;
	
	public List<NewsDetail> getNewsHot(){
		return newsDetailDAO.getNewsHot();
	}
	
	public List<NewsDetail> getAllNews(){
		return newsDetailDAO.getAllNews();
	}
	
	public List<NewsDetail> getNewsById(int newsId) {
		return newsDetailDAO.getNewsById(newsId);
	}
	
	public int insertNewsDetail(NewsDetail newsDetail) {
		return newsDetailDAO.insertNewsDeatail(newsDetail);
	}
	
	public boolean deleteNewsDetail(int newsID) {
		return newsDetailDAO.deleteNewsDetail(newsID);
	}
	
	public boolean updateNewsDeTail(int newsID, NewsDetail newsDetail) {
		return newsDetailDAO.updateNewsDetail(newsID, newsDetail);
	}
	
	public long getPageSize(long show) {
		return newsDetailDAO.getPageSize(show);
	}
	
	public List<NewsDetail> getListNewsByPage(int show, int page) {
		return newsDetailDAO.getListNewsByPage(show, page);
	}
	
	public List<NewsDetail> getNewsByCategory(int categoryNewsId) {
		return newsDetailDAO.getNewsByCategoryId(categoryNewsId);
	}
}
