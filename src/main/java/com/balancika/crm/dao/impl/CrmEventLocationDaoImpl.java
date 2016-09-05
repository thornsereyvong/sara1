package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmEventLocationDao;
import com.balancika.crm.model.CrmEventLocation;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmEventLocationDaoImpl extends CrmIdGenerator implements CrmEventLocationDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertEventLocation(CrmEventLocation location) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			location.setLoId(IdAutoGenerator("LOC"));
			session.save(location);
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
	public boolean updateEventLocation(CrmEventLocation location) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(location);
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
	public String deleteEventLocation(String id) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query  = session.createSQLQuery("DELETE FROM crm_location WHERE LO_ID = :loId");
			query.setParameter("loId", id);
			session.getTransaction().commit();
			if(query.executeUpdate() > 0){
				session.close();
				return "OK";
			}
		} catch (ConstraintViolationException ex){
			ex.getMessage();
			session.getTransaction().rollback();
			session.close();
			return "FOREIGN_KEY_CONSTRAIN";
		}
		catch (HibernateException e) {
			e.getMessage();
			session.getTransaction().rollback();
			session.close();
		}
		return "FAILED";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmEventLocation> listEventLocations() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmEventLocation.class);
		criteria.addOrder(Order.asc("loId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmEventLocation findEventLocationById(String id) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmEventLocation.class);
		criteria.add(Restrictions.eq("loId", id));
		return (CrmEventLocation)criteria.uniqueResult();
	}

}
