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

import com.balancika.crm.dao.CrmCaseTypeDao;
import com.balancika.crm.model.CrmCaseType;

@Repository
public class CrmCaseTypeDaoImpl implements CrmCaseTypeDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertCaseType(CrmCaseType type) {
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
	public boolean updateCaseType(CrmCaseType type) {
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
	public String deleteCaseType(int typeId) {
		Session session = transactionManager.getSessionFactory().openSession();
		CrmCaseType type = new CrmCaseType();
		try {
			session.beginTransaction();
			type.setCaseTypeId(typeId);
			session.delete(type);
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
	public List<CrmCaseType> listCaseTypes() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmCaseType.class);
		criteria.addOrder(Order.asc("caseTypeId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmCaseType findCaseTypeById(int typeId) {
		return (CrmCaseType) transactionManager.getSessionFactory().getCurrentSession().get(CrmCaseType.class, typeId);
	}

}
