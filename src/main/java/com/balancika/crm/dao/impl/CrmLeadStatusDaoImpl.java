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

import com.balancika.crm.dao.CrmLeadStatusDao;
import com.balancika.crm.model.CrmLeadStatus;

@Repository
public class CrmLeadStatusDaoImpl implements CrmLeadStatusDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertLeadStatus(CrmLeadStatus status) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(status);
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
	public boolean updateLeadStatus(CrmLeadStatus status) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(status);
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
	public String deleteLeadStatus(int statusID) {

		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmLeadStatus status = new CrmLeadStatus();
			status.setStatusID(statusID);
			session.delete(status);
			session.getTransaction().commit();
			return "OK";
		} catch (ConstraintViolationException ex) {
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
	public List<CrmLeadStatus> getAllLeadStatus() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmLeadStatus.class);
		criteria.addOrder(Order.asc("statusID"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmLeadStatus findLeadStatusById(int statusID) {

		return (CrmLeadStatus) transactionManager.getSessionFactory().getCurrentSession()
				.get(CrmLeadStatus.class, statusID);
	}

}
