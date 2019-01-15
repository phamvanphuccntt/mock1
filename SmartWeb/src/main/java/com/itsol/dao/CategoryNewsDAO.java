package com.itsol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.itsol.model.CategoryNews;

@Repository
public class CategoryNewsDAO {
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@SuppressWarnings("unchecked")
	public List<CategoryNews> getListCategoryNews() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<CategoryNews> listCategoryNews = null;
		try {
			transaction = session.beginTransaction();
			listCategoryNews = session.createQuery("from CategoryNews").list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listCategoryNews;
	}

	public int insertCategoryNews(CategoryNews categoryNews) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int id = -1;
		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(categoryNews);
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

	public boolean deleteCategoryNews(int categoryNewsId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CategoryNews categoryNews = (CategoryNews) session.get(CategoryNews.class, categoryNewsId);
			session.delete(categoryNews);
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

	public boolean updateCategoryNews(int categoryNewsID, CategoryNews categoryNews) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			categoryNews.setCategoryNewsId(categoryNewsID);
			session.update(categoryNews);
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
}
