package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmUserActivityDao;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmUserActivityDaoImpl implements CrmUserActivityDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addUserActivity(CrmUserActivity activity) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(activity.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(activity);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmUserActivity> listUserActivities(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmUserActivity.class);
			session.getTransaction().commit();
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmUserActivity> listUserActivitiesWithSpecificUser(String username, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmUserActivity.class);
			criteria.add(Restrictions.eq("byUser", username));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

}
