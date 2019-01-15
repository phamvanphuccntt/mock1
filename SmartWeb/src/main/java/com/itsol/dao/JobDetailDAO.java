package com.itsol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.itsol.model.JobDetail;
@Repository
public class JobDetailDAO {
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	// Lay danh sach cong viec
	@SuppressWarnings("unchecked")
	public List<JobDetail> getAllJob() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<JobDetail> listJob = null;
		try {
			transaction = session.beginTransaction();
			listJob = session.createQuery("FROM JobDetail").list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listJob;
	}
	//Lay chi tiet cong viec
	@SuppressWarnings("unchecked")
	public List<JobDetail> getJobById(int jobId){
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<JobDetail> listJobDetail = null;
		try {
			transaction = session.beginTransaction();
			Query<JobDetail> query = session.createQuery("from JobDetail where jobId = :jobId");
			query.setParameter("jobId", jobId);
			listJobDetail = query.list();
			transaction.commit();

		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listJobDetail;
	}

	// Lay danh sach theo danh muc cong viec
	/*@SuppressWarnings("unchecked")
	public List<JobDetail> getJobByCategoryId(int categoryJobId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<JobDetail> listJobByCategory = null;
		try {
			transaction = session.beginTransaction();
			Query<JobDetail> query = session.createQuery("FROM JobDetail WHERE categoryJobId =:id");
			query.setParameter("id", categoryJobId);
			listJobByCategory= query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listJobByCategory;
	}*/

	// Lay 4 Job hot trang Home
	@SuppressWarnings("unchecked")
	public List<JobDetail> getJobHot() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<JobDetail> listJobHot = null;
		try {
			transaction = session.beginTransaction();
			listJobHot = session.createQuery(
					"from JobDetail where HOT = 1 and  rownum <= 4 ORDER BY jobId DESC")
					.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listJobHot;
	}
	
	public boolean updateJobDetail(int jobId, JobDetail jobDetail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			jobDetail.setJobId(jobId);
			session.update(jobDetail);
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
	
	public boolean deleteJobDetail(int jobId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			JobDetail jobDetail = session.get(JobDetail.class, jobId);
			session.delete(jobDetail);
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
	
	public int insertJobDetail(JobDetail jobDetail) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int id = -1;
		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(jobDetail);
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
	
	public long getPageSize(long show) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		long page = -1;
		try {
			transaction = session.beginTransaction();
			long total = (Long)session.createQuery("select count(*) from JobDetail").uniqueResult();
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
	public List<JobDetail> getListJobByPage(int show, int page) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<JobDetail> listJob = null;
		try {
			transaction = session.beginTransaction();
			int index = show*(page-1);
			Query<JobDetail> query = session.createQuery("from JobDetail order by jobId desc");
			query.setFirstResult(index);
			query.setMaxResults(show);
			listJob = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listJob;
	}
}
