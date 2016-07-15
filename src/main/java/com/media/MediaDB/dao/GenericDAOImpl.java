package com.media.MediaDB.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.tapestry5.annotations.SetupRender;
import org.apache.tapestry5.hibernate.HibernateSessionManager;
import org.apache.tapestry5.hibernate.annotations.CommitAfter;
import org.apache.tapestry5.ioc.annotations.Inject;
import org.apache.tapestry5.ioc.annotations.ServiceId;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

@ServiceId("GenericDAO")
public class GenericDAOImpl<T> implements GenericDAO<T> {

	@Inject
	HibernateSessionManager hibernateSessionManager;

	@Override
	public <T> T save(final T o) {
		Session session = hibernateSessionManager.getSession();
		T obj = (T) session.save(o);
		// session.persist(o);
		hibernateSessionManager.commit();
		session.flush();
		return obj;
	}

	@Override
	public void delete(final T o) {
		Session session = hibernateSessionManager.getSession();
		session.delete(o);
		hibernateSessionManager.commit();
		session.flush();
	}

	@Override
	public <T> T get(final Class<T> type, final long id) {
		Session session = hibernateSessionManager.getSession();
		T obj = (T) session.get(type, id);
		return obj;
	}

	@Override
	public <T> T merge(final T o) {
		Session session = hibernateSessionManager.getSession();
		T obj = (T) session.merge(o);
		hibernateSessionManager.commit();
		session.flush();
		return obj;
	}

	@Override
	public <T> void saveOrUpdate(final T o) {
		Session session = hibernateSessionManager.getSession();
		session.saveOrUpdate(o);
		hibernateSessionManager.commit();
		session.flush();
	}

	@Override
	public <T> List<T> getAll(final Class<T> type) {
		Session session = hibernateSessionManager.getSession();
		final Criteria criteria = session.createCriteria(type);
		return criteria.list();
	}

}
