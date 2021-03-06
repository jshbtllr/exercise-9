package com.exercise9.core.dao;

import org.hibernate.SessionFactory;

public class GenericDAOImpl <T> implements GenericDAOInterface <T> {
	SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public T add(T added) {
		sessionFactory.getCurrentSession().save(added);
		return added;
	}

	public T update(T updated) {
		sessionFactory.getCurrentSession().update(updated);
		return updated;
	}	

	public T delete(T deleted) {
		sessionFactory.getCurrentSession().delete(deleted);
		return deleted;
	}	

	public T get(Class<T> entity, Long id) {
		T details = null;
		details = (T) sessionFactory.getCurrentSession().get(entity, id);
		return details;
	}
}