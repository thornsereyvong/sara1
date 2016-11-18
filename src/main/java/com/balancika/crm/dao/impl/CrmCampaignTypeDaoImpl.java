package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCampaignTypeDao;
import com.balancika.crm.model.CrmCampaignType;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmCampaignTypeDaoImpl implements CrmCampaignTypeDao {
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addCampaignType(CrmCampaignType type) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(type.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(type);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateCampaignType(CrmCampaignType type) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(type.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(type);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public String deleteCampaignType(CrmCampaignType type) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(type.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(type);
			session.getTransaction().commit();
			return "OK";
		}catch (ConstraintViolationException ex) {
			session.getTransaction().rollback();
			ex.printStackTrace();
			return "FOREIGN_KEY_CONSTRAIN";
		}catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			return "FAILED";
		}
		finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCampaignType> listAllCampaignType(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCampaignType.class);
			criteria.addOrder(Order.asc("typeID"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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

	@Override
	public CrmCampaignType findCampaignTypeById(int typeID, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			return (CrmCampaignType) session.get(CrmCampaignType.class, typeID);
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
