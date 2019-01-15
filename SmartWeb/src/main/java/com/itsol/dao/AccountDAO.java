package com.itsol.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import com.itsol.model.Account;

@Repository
public class AccountDAO {

	private  SessionFactory sessionFactory = new Configuration().configure().buildSessionFactory();

	@SuppressWarnings("unchecked")
	public List<Account> getListAccount() {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Account> listAccount = null;
		try {
			transaction = session.beginTransaction();
			listAccount = session.createQuery("from Account").list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listAccount;
	}

	// GET ACCOUNT FROM X TO Y
	@SuppressWarnings("unchecked")
	public List<Account> getListAccountAmount(int amount) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Account> listAccount = null;
		try {
			transaction = session.beginTransaction();
			Query<Account> query = session.createQuery("from Account");
			query.setFirstResult(0);
			query.setMaxResults(amount);
			listAccount = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listAccount;
	}

	// GET ACCOUNT FROM Min TO Max
	@SuppressWarnings("unchecked")
	public List<Account> getListPagenation(int MIN, int max) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		List<Account> listAccount = null;
		try {
			transaction = session.beginTransaction();
			Query<Account> query = session.createQuery("from Account");
			query.setFirstResult(MIN);
			query.setMaxResults(max);
			listAccount = query.list();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return listAccount;
	}

	@SuppressWarnings("unchecked")
	public Account getAnAccountByUsername(String username) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Account account = null;
		try {
			transaction = session.beginTransaction();
			Query<Account> query = session.createQuery("from Account where username=:username");
			query.setParameter("username", username);
			account = query.uniqueResult();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}

	// LẤY SỐ LƯỢNG PAGE PHÂN TRANG
	public Long countPage(int show) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Long size = null;
		try {
			transaction = session.beginTransaction();
			Long ammout = (Long) session.createQuery("select count(*) from Account").uniqueResult();
			transaction.commit();
			if (ammout % show == 0) {
				size = ammout / show;
			} else {
				size = (ammout / show) + 1;
			}
		} catch (HibernateException ex) {
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return size;
	}

	// GET ACCOUNT BY ID
	public Account getAnAccountById(int accountId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Account account = null;
		try {
			transaction = session.beginTransaction();
			account = session.get(Account.class, accountId);
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}

	public   String insertAccount(Account account) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(account);
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
			return "Fail";
		} finally {
			session.close();
		}
		return "Success";
	}

	public boolean deleteAccount(int accountId) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Account account = (Account) session.get(Account.class, accountId);
			session.delete(account);
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

	// CẬP NHẬP THÔNG TIN TÀI KHOẢN
	public String updateAccount(String username, Account account) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Account acc = getAnAccountByUsername(username);
			account.setAccountId(acc.getAccountId());
			account.setUsername(acc.getUsername());
			session.update(account);
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
			return "fail";
		} finally {
			session.close();
		}
		return "success";
	}

	// ACTIVE TÀI KHOẢN FOLLOWING USER NAME
	public boolean Activeaccount(String username) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			Account acc = getAnAccountByUsername(username);
			acc.setStatus(true);
			session.update(acc);
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

	// GET ACCOUNT BY EMAIL
	@SuppressWarnings("unchecked")
	public Account getAnAccountByEmail(String email) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Account account = null;
		try {
			transaction = session.beginTransaction();
			Query<Account> query = session.createQuery("from Account where email=:email");
			query.setParameter("email", email);
			account = query.uniqueResult();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}

	// GET ACCOUNT BY PHONE
	@SuppressWarnings("unchecked")
	public Account getAnAccountByPhone(String phone) {
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Account account = null;
		try {
			transaction = session.beginTransaction();
			Query<Account> query = session.createQuery("from Account where phone=:phone");
			query.setParameter("phone", phone);
			account = query.uniqueResult();
			transaction.commit();
		} catch (HibernateException ex) {
			if (transaction != null)
				transaction.rollback();
			ex.printStackTrace();
		} finally {
			session.close();
		}
		return account;
	}

}
