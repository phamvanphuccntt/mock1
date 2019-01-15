package com.itsol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.itsol.model.Slider;

@Repository
public class SliderDAO {
	
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();
	
	@SuppressWarnings("unchecked")
	public List<Slider> getListSlider() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Slider> listSlider = null;
		try {
			transaction = session.beginTransaction();
			listSlider = session.createQuery("from Slider").list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listSlider;
	}
	
	public boolean updateSlider(int slideId, Slider slider) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			slider.setSlideId(slideId);
			session.update(slider);
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
