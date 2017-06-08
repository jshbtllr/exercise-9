package com.exercise9.core.dao;

import com.exercise9.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.Query;
import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

public class GenericDAO implements GenericDAOInterface {

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Type add(Type added) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.save(added);
			transaction.commit();
		} catch(HibernateException he) {
			if (transaction != null)  {
				transaction.rollback();
			}
			System.out.println("Error encountered while adding");
			he.printStackTrace();
		} finally {
			session.close();
		}
		return added;
	}

	public Type update(Type updated) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.update(updated);
			transaction.commit();
		} catch (HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
		} finally {
			session.close();
		}
		return updated;
	}	

	public static Type delete(Type deleted) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;

		try {
			transaction = session.beginTransaction();
			session.delete(deleted);
			transaction.commit();
		} catch(HibernateException he) {
			if (transaction != null) {
				transaction.rollback();
			}
			System.out.println("Error encountered deleting role");
			he.printStackTrace();
		} finally {
			session.close();
		}
		return deleted;
	}	

	public Type get(Class<Type> entity, Long id) {
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = null;
		Type details = null;

		try {
			transaction = session.beginTransaction();
			details = (Type) session.get(entity, id);
		} catch(HibernateException he) {
			if(transaction != null) {
				transaction.rollback();
			}
			System.out.println("Error getting employee");
			he.printStackTrace();
		} finally {
			session.close();
		}
		return details;
	}
}