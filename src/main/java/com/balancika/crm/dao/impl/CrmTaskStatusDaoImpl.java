package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmTaskStatusDao;
import com.balancika.crm.model.CrmTaskStatus;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmTaskStatusDaoImpl implements CrmTaskStatusDao{

	@Override
	public boolean insertTaskStatus(CrmTaskStatus status) {
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
	public boolean updateTaskStatus(CrmTaskStatus status) {
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
	public String deleteTaskStatus(CrmTaskStatus status) {
		Session session = new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(status);
			session.getTransaction().commit();
			return "OK";
		} catch(ConstraintViolationException ex){
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
	public List<CrmTaskStatus> lisTaskStatus(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmTaskStatus.class);
			criteria.addOrder(Order.asc("taskStatusId"));
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
	public CrmTaskStatus findTaskStatusById(int statusId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmTaskStatus.class);
			criteria.add(Restrictions.eq("taskStatusId", statusId));
			return (CrmTaskStatus)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
