package com.itsol.dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import com.itsol.model.Setting;

@Repository
public class SettingDAO {
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	public Setting getListSetting(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Setting st = null;
		try {
			transaction = session.beginTransaction();
			st = session.get(Setting.class, id);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return st;
	}

	public boolean updateSetting(int settingId, Setting setting) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			setting.setSettingId(settingId);
			session.update(setting);
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

}
