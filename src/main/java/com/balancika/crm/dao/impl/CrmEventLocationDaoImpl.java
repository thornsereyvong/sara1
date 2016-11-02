package com.balancika.crm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmEventLocationDao;
import com.balancika.crm.model.CrmEventLocation;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmEventLocationDaoImpl extends CrmIdGenerator implements CrmEventLocationDao{
	
	@Override
	public boolean insertEventLocation(CrmEventLocation location) {
		Session session = HibernateSessionFactory.getSessionFactory(location.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			location.setLoId(IdAutoGenerator("LOC"));
			location.setLoCreateDate(new Date());
			session.save(location);
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
	public boolean updateEventLocation(CrmEventLocation location) {
		Session session = HibernateSessionFactory.getSessionFactory(location.getMeDataSource()).openSession();
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
	public String deleteEventLocation(CrmEventLocation location) {
		Session session = HibernateSessionFactory.getSessionFactory(location.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			SQLQuery query  = session.createSQLQuery("DELETE FROM crm_location WHERE LO_ID = :loId");
			query.setParameter("loId", location.getLoId());
			session.getTransaction().commit();
			if(query.executeUpdate() > 0){
				session.clear();
				session.close();
				return "OK";
			}
		} catch (ConstraintViolationException ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
			return "FOREIGN_KEY_CONSTRAIN";
		}
		catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
		}
		return "FAILED";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmEventLocation> listEventLocations(MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmEventLocation.class);
			criteria.addOrder(Order.asc("loId"));
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
	public CrmEventLocation findEventLocationById(String id, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		
		try {
			Criteria criteria = session.createCriteria(CrmEventLocation.class);
			criteria.add(Restrictions.eq("loId", id));
			return (CrmEventLocation)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
