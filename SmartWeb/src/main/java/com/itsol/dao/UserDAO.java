package com.itsol.dao;

import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;
import com.itsol.model.User;

@Repository
public class UserDAO {
	private SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@SuppressWarnings("unchecked")
	public List<User> getListUser() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<User> listUser = null;
		try {
			transaction = session.beginTransaction();
			listUser = session.createQuery("from User").list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listUser;
	}

	public User getUserId(int id) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		User u = null;
		try {
			transaction = session.beginTransaction();
			u = (User) session.get(User.class, id);
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

	public int insertUser(User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		int id = -1;
		try {
			transaction = session.beginTransaction();
			id = (int) session.save(user);
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

	public boolean updateUser(int userId, User user) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			user.setUserId(userId);
			session.update(user);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

	public boolean deleteUser(int userId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			User user = session.get(User.class, userId);
			session.delete(user);
			transaction.commit();
		} catch (HibernateException e) {
			if (transaction != null)
				transaction.rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return true;
	}

}
