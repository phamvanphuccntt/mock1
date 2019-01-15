package com.itsol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.itsol.model.ApplyProfile;


@Repository
public class ApplyProfileDAO {
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	//Ung tuyen
	public int insertProfile(ApplyProfile applyProfile) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int id = -1;
		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(applyProfile);
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
	
	@SuppressWarnings("unchecked")
	public List<ApplyProfile> getListApplyProfile() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<ApplyProfile> listApply = null;
		try {
			transaction = session.beginTransaction();
			listApply = session.createQuery("from ApplyProfile").list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listApply;
	}
	
	public boolean updateApplyProfile(int applyProfleId, ApplyProfile applyProfile) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			applyProfile.setApplyProfleId(applyProfleId);
			session.update(applyProfile);
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
	
	public long getPageSize(long show) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		long page = -1;
		try {
			transaction = session.beginTransaction();
			long total = (Long)session.createQuery("select count(*) from ApplyProfile").uniqueResult();
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
	public List<ApplyProfile> getListJobByPage(int show, int page) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<ApplyProfile> listApply = null;
		try {
			transaction = session.beginTransaction();
			int index = show*(page-1);
			Query<ApplyProfile> query = session.createQuery("from ApplyProfile order by applyProfleId desc");
			query.setFirstResult(index);
			query.setMaxResults(show);
			listApply = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listApply;
	}
	@SuppressWarnings("unchecked")
	public List<ApplyProfile> getListApplyProfiles(int accountId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<ApplyProfile> list = null;
		try {
			transaction = session.beginTransaction();
			String sql = "FROM APPLY_PROFILE where ACCOUNT_ID=:ACCOUNT_ID";
			Query<ApplyProfile> query = session.createQuery(sql);
			query.setParameter("ACCOUNT_ID", accountId);
			list = query.list();
			transaction.commit();
		} catch (HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
			he.printStackTrace();
		} finally {
			session.close();
		}
		return list;
	}
	
	public ApplyProfile getApplyProfile(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		ApplyProfile u = null;
		try {
			transaction = session.beginTransaction();
			u = (ApplyProfile) session.get(ApplyProfile.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return u;
	}
	@SuppressWarnings("unchecked")
	public ApplyProfile getApplyProfile() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<ApplyProfile> listApply = null;
		try {
			transaction = session.beginTransaction();
			listApply = session.createQuery("from ApplyProfile").list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return (ApplyProfile) listApply;
	}
	
}
