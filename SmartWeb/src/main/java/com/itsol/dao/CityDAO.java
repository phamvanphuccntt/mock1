package com.itsol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.itsol.model.City;

@Repository
public class CityDAO {
	
private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@SuppressWarnings("unchecked")
	public List<City> getListCity() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<City> listCity = null;
		try {
			transaction = session.beginTransaction();
			listCity = session.createQuery("from City").list();
			transaction.commit();
		} catch(HibernateException ex) {
			if(transaction!=null) transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listCity;
	}
	
	public int insertCity(City city) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int id = -1;
		try {
			transaction = session.beginTransaction();
			id = (Integer) session.save(city);
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

}
