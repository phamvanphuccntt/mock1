package com.itsol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.itsol.model.CategoryJob;

@Repository
public class CategoryJobDAO {
	
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@SuppressWarnings("unchecked")
	public List<CategoryJob> getListCategoryJob() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<CategoryJob> listCategoryJob = null;
		try {
			transaction = session.beginTransaction();
			listCategoryJob = session.createQuery("from CategoryJob").list();
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction!=null) transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listCategoryJob;
	}
	
	public int insertCategoryJob(CategoryJob categoryJob) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int id = -1;
		try {
			transaction = session.beginTransaction();
			id = (Integer)session.save(categoryJob);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction!=null) transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return id;
	}
	
	public boolean deleteCategoryJob(int categoryJobId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			CategoryJob categoryJob = (CategoryJob)session.get(CategoryJob.class, categoryJobId);
			session.delete(categoryJob);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction!=null) transaction.rollback();
			ex.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
	public boolean updateCategoryJob(int categoryJobId, CategoryJob categoryJob) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			categoryJob.setCategoryJobId(categoryJobId);
			session.update(categoryJob);
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction!=null) transaction.rollback();
			ex.printStackTrace();
			return false;
		} finally {
			session.close();
		}
		return true;
	}
	
}
