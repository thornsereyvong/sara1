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

import com.balancika.crm.dao.CrmOpportunityTypeDao;
import com.balancika.crm.model.CrmOpportunityType;

@Repository
public class CrmOpportunityTypeDaoImpl implements CrmOpportunityTypeDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertOpportunityType(CrmOpportunityType type) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(type);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateOpportunityType(CrmOpportunityType type) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(type);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public String deleteOpportunityType(int typeID) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmOpportunityType type = new CrmOpportunityType();
			type.setOtId(typeID);
			session.delete(type);
			session.getTransaction().commit();
			return "OK";
		} catch(ConstraintViolationException ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
			return "FOREIGN_KEY_CONSTRAIN";
		}catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return "FAILED";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunityType> listOpportunityTypes() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmOpportunityType.class);
		criteria.addOrder(Order.asc("otId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// criteria.setProjection(Projections.groupProperty(""));
		return criteria.list();
	}

	@Override
	public CrmOpportunityType findOpportunityTypeById(int typeID) {
		return (CrmOpportunityType) transactionManager.getSessionFactory().getCurrentSession()
				.get(CrmOpportunityType.class, typeID);
	}

}
