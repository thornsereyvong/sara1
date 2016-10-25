package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCampaignTypeDao;
import com.balancika.crm.model.CrmCampaignType;

@Repository
public class CrmCampaignTypeDaoImpl implements CrmCampaignTypeDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean addCampaignType(CrmCampaignType type) {

		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(type);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateCampaignType(CrmCampaignType type) {

		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(type);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public String deleteCampaignType(int typeID) {
		Session session = transactionManager.getSessionFactory().openSession();
		CrmCampaignType type = new CrmCampaignType();
		try {
			session.beginTransaction();
			type.setTypeID(typeID);
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
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCampaignType> listAllCampaignType() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmCampaignType.class);
		criteria.addOrder(Order.asc("typeID"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// criteria.setProjection(Projections.groupProperty(""));
		return criteria.list();
	}

	@Override
	public CrmCampaignType findCampaignTypeById(int typeID) {
		return (CrmCampaignType) transactionManager.getSessionFactory().getCurrentSession().get(CrmCampaignType.class, typeID);
	}

}
