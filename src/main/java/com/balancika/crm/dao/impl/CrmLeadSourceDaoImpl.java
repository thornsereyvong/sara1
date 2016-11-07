package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmLeadSourceDao;
import com.balancika.crm.model.CrmLeadSource;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmLeadSourceDaoImpl implements CrmLeadSourceDao {

	@Override
	public boolean insertLeadSource(CrmLeadSource source) {
		Session session = new HibernateSessionFactory().getSessionFactory(source.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(source);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateLeadSource(CrmLeadSource source) {

		Session session = new HibernateSessionFactory().getSessionFactory(source.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(source);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public String deleteLeadSource(CrmLeadSource source) {

		Session session = new HibernateSessionFactory().getSessionFactory(source.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(source);
			session.getTransaction().commit();
			return "OK";
		} catch (ConstraintViolationException ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
		}
		return "FAILED";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLeadSource> getAllLeadSource(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmLeadSource.class);
			criteria.addOrder(Order.asc("sourceID"));
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
	public CrmLeadSource findLeadSourceById(int sourceID, MeDataSource dataSource) {

		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			return (CrmLeadSource) session.get(CrmLeadSource.class, sourceID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
