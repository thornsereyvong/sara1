package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCaseStatusDao;
import com.balancika.crm.model.CrmCaseStatus;

@Repository
public class CrmCaseStatusDaoImpl implements CrmCaseStatusDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertCaseStatus(CrmCaseStatus status) {
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
	public boolean updateCaseStatus(CrmCaseStatus status) {
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
	public String deleteCaseStatus(int statusId) {
		Session session = transactionManager.getSessionFactory().openSession();
		CrmCaseStatus status = new CrmCaseStatus();
		try {
			session.beginTransaction();
			status.setStatusId(statusId);
			session.delete(status);
			session.getTransaction().commit();
			return "OK";
		} catch (ConstraintViolationException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return "FAILED";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCaseStatus> listCaseStatus() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmCaseStatus.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmCaseStatus findCaseStatusById(int statusId) {
		return (CrmCaseStatus) transactionManager.getSessionFactory().getCurrentSession()
				.get(CrmCaseStatus.class, statusId);
	}

}
