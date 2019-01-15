package com.itsol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.itsol.model.NewsDetail;

@Repository
public class NewsDetailDAO {
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@SuppressWarnings("unchecked")
	public List<NewsDetail> getAllNews() {

		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		List<NewsDetail> listNewsDetail = null;
		try {
			transaction = session.beginTransaction();
			listNewsDetail = session.createQuery("from NewsDetail").list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listNewsDetail;
	}
	
	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsByCategoryId(int categoryNewsId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<NewsDetail> listNewsByCategory = null;
		try {
			transaction = session.beginTransaction();
			Query<NewsDetail> query = session.createQuery("FROM NewsDetail WHERE categoryNews.categoryNewsId =:id");
			query.setParameter("id", categoryNewsId);
			listNewsByCategory = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listNewsByCategory;
	}

	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsById(int newsId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<NewsDetail> listNewsDetail = null;
		try {
			transaction = session.beginTransaction();
			Query<NewsDetail> query = session.createQuery("from NewsDetail where newsID = :id");
			query.setParameter("id", newsId);
			listNewsDetail = query.list();
			transaction.commit();

		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listNewsDetail;
	}

	public int insertNewsDeatail(NewsDetail newsDetail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int id = -1;
		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(newsDetail);
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}

	public boolean deleteNewsDetail(int newsID) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			NewsDetail newsDetail = (NewsDetail) session.get(NewsDetail.class, newsID);
			session.delete(newsDetail);
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	public boolean updateNewsDetail(int newsID, NewsDetail newsDetail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			newsDetail.setNewsID(newsID);
			session.update(newsDetail);
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	public List<NewsDetail> getNewsHot() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<NewsDetail> listNewsHot = null;
		try {
			transaction = session.beginTransaction();
			listNewsHot = session.createQuery(
					"from NewsDetail where HOT = 1 and  rownum <= 4 ORDER BY newsID DESC")
					.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listNewsHot;
	}
	
	public long getPageSize(long show) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		long page = -1;
		try {
			transaction = session.beginTransaction();
			long total = (Long)session.createQuery("select count(*) from NewsDetail").uniqueResult();
			if(total%show==0) {
				page = total/show;
			} else {
				page = (total/show)+1;
			}
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<NewsDetail> getListNewsByPage(int show, int page) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<NewsDetail> listNews = null;
		try {
			transaction = session.beginTransaction();
			int index = show*(page-1);
			Query<NewsDetail> query = session.createQuery("from NewsDetail order by newsID desc");
			query.setFirstResult(index);
			query.setMaxResults(show);
			listNews = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listNews;
	}

}
