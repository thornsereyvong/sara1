package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmLeadStatusDao;
import com.balancika.crm.model.CrmLeadStatus;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmLeadStatusDaoImpl implements CrmLeadStatusDao {

	@Override
	public boolean insertLeadStatus(CrmLeadStatus status) {
		Session session = new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(status);
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
	public boolean updateLeadStatus(CrmLeadStatus status) {
		Session session = new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(status);
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
	public String deleteLeadStatus(CrmLeadStatus status) {

		Session session = new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(status);
			session.getTransaction().commit();
			return "OK";
		} catch (ConstraintViolationException ex) {
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
	public List<CrmLeadStatus> getAllLeadStatus(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmLeadStatus.class);
			criteria.addOrder(Order.asc("statusID"));
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
	public CrmLeadStatus findLeadStatusById(int statusID, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			return (CrmLeadStatus) session.get(CrmLeadStatus.class, statusID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
