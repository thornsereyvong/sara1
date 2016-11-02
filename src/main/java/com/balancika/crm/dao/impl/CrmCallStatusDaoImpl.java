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
import com.balancika.crm.dao.CrmCallStatusDao;
import com.balancika.crm.model.CrmCallStatus;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmCallStatusDaoImpl implements CrmCallStatusDao {

	@Override
	public boolean insertCallStatus(CrmCallStatus status) {
		Session session = HibernateSessionFactory.getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(status);
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
	public boolean updateCallStatus(CrmCallStatus status) {
		Session session = HibernateSessionFactory.getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(status);
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
	public String deleteCallStatus(CrmCallStatus status) {
		Session session = HibernateSessionFactory.getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(status);
			session.getTransaction().commit();
			return "OK";
		} catch(ConstraintViolationException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
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
	public List<CrmCallStatus> listCallStatus(MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCallStatus.class);
			criteria.addOrder(Order.asc("callStatusId"));
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
	public CrmCallStatus findCallStatusById(CrmCallStatus status) {
		Session session = HibernateSessionFactory.getSessionFactory(status.getMeDataSource()).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCallStatus.class);
			criteria.add(Restrictions.eq("callStatusId", status.getCallStatusId()));
			return (CrmCallStatus) criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
}
