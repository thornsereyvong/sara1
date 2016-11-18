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
import com.balancika.crm.dao.CrmCampaignStatusDao;
import com.balancika.crm.model.CrmCampaignStatus;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmCampaignStatusDaoImpl implements CrmCampaignStatusDao {
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addCampaignStatus(CrmCampaignStatus status) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try{
			session.beginTransaction();
			session.save(status);
			session.getTransaction().commit();
			return true;
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateCampaignStatus(CrmCampaignStatus status) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try{
			session.beginTransaction();
			session.update(status);
			session.getTransaction().commit();
			return true;
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public String deleteCampaignStatus(CrmCampaignStatus status) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try{
			session.beginTransaction();
			session.delete(status);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return "OK";
		} catch (ConstraintViolationException e){
			session.getTransaction().rollback();
			e.printStackTrace();
			session.clear();
			session.close();
			sessionFactory.close();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			session.clear();
			session.close();
			sessionFactory.close();
			return "FAILED";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCampaignStatus> listAllCampaignStatus(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCampaignStatus.class);
			criteria.addOrder(Order.asc("statusID"));
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
	public CrmCampaignStatus findCampaignStatusById(int statusID, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			return (CrmCampaignStatus)session.get(CrmCampaignStatus.class, statusID);
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
