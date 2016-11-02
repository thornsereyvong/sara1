package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCaseTypeDao;
import com.balancika.crm.model.CrmCaseType;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmCaseTypeDaoImpl implements CrmCaseTypeDao {


	@Override
	public boolean insertCaseType(CrmCaseType type) {
		Session session = HibernateSessionFactory.getSessionFactory(type.getMeDataSource()).openSession();
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
		}
		return false;
	}

	@Override
	public boolean updateCaseType(CrmCaseType type) {
		Session session = HibernateSessionFactory.getSessionFactory(type.getMeDataSource()).openSession();
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
		}
		return false;
	}

	@Override
	public String deleteCaseType(CrmCaseType type) {
		Session session = HibernateSessionFactory.getSessionFactory(type.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
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
			session.clear();
			session.close();
		}
		return "FAILED";

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCaseType> listCaseTypes(MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCaseType.class);
			criteria.addOrder(Order.asc("caseTypeId"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public CrmCaseType findCaseTypeById(int typeId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			return (CrmCaseType) session.get(CrmCaseType.class, typeId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
