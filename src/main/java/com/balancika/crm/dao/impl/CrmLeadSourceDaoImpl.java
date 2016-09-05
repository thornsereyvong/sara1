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

import com.balancika.crm.dao.CrmLeadSourceDao;
import com.balancika.crm.model.CrmLeadSource;

@Repository
public class CrmLeadSourceDaoImpl implements CrmLeadSourceDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertLeadSource(CrmLeadSource source) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(source);
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
	public boolean updateLeadSource(CrmLeadSource source) {

		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(source);
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
	public String deleteLeadSource(int sourceID) {

		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmLeadSource source = new CrmLeadSource();
			source.setSourceID(sourceID);
			session.delete(source);
			session.getTransaction().commit();
			return "OK";
		} catch (ConstraintViolationException ex){
			ex.getMessage();
			session.getTransaction().rollback();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch (HibernateException e) {
			e.getMessage();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return "FAILED";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLeadSource> getAllLeadSource() {

		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmLeadSource.class);
		criteria.addOrder(Order.asc("sourceID"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// criteria.setProjection(Projections.groupProperty(""));
		return criteria.list();
	}

	@Override
	public CrmLeadSource findLeadSourceById(int sourceID) {

		return (CrmLeadSource) transactionManager.getSessionFactory().getCurrentSession()
				.get(CrmLeadSource.class, sourceID);
	}

}
