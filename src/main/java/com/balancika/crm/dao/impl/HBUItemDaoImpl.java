package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.HBUItemDao;
import com.balancika.crm.model.HBUItem;
import com.balancika.crm.model.HBUItemCompetitor;
import com.balancika.crm.model.HBUItemCustomer;
import com.balancika.crm.model.MeDataSource;

@Repository
public class HBUItemDaoImpl implements HBUItemDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addCompetitorsToItem(HBUItemCompetitor itemCompetitor) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(itemCompetitor.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(itemCompetitor);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateCompetitorOfItem(HBUItem item) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HBUItem findHBUItemById(String itemId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(HBUItem.class);
			criteria.add(Restrictions.eq("itemId", itemId));
			return (HBUItem)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@Override
	public boolean addCustomerOfItem(HBUItemCustomer itemCustomer) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(itemCustomer.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.saveOrUpdate(itemCustomer);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}


}
