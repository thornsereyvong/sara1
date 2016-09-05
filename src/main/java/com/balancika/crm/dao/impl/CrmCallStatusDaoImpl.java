package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCallStatusDao;
import com.balancika.crm.model.CrmCallStatus;

@Repository
public class CrmCallStatusDaoImpl implements CrmCallStatusDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertCallStatus(CrmCallStatus status) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(status);
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
	public boolean updateCallStatus(CrmCallStatus status) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(status);
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
	public String deleteCallStatus(int statusId) {
		Session session = transactionManager.getSessionFactory().openSession();
		CrmCallStatus status = new CrmCallStatus();
		try {
			session.beginTransaction();
			status.setCallStatusId(statusId);
			session.delete(status);
			session.getTransaction().commit();
			return "OK";
		} catch(ConstraintViolationException ex){
			session.getTransaction().rollback();
			ex.getMessage();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			return "FAILED";
		} finally {
			session.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCallStatus> listCallStatus() {
		Criteria criteria = transactionManager.getSessionFactory().getCurrentSession()
				.createCriteria(CrmCallStatus.class);
		criteria.addOrder(Order.asc("callStatusId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmCallStatus findCallStatusById(int statusId) {
		Criteria criteria = transactionManager.getSessionFactory().getCurrentSession()
				.createCriteria(CrmCallStatus.class);
		criteria.add(Restrictions.eq("callStatusId", statusId));
		return (CrmCallStatus) criteria.uniqueResult();
	}
}
