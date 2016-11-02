package com.balancika.crm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmOpportunityDetailsDao;
import com.balancika.crm.model.AmeClass;
import com.balancika.crm.model.AmeItem;
import com.balancika.crm.model.AmeLocation;
import com.balancika.crm.model.AmeUom;
import com.balancika.crm.model.CrmOpportunityDetails;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmOpportunityDetailsDaoImpl implements CrmOpportunityDetailsDao{
	
	@Override
	public boolean insertOpportunityDetails(CrmOpportunityDetails details) {
		Session session = HibernateSessionFactory.getSessionFactory(details.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(details);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
		}
		return false;
	}
	

	@Override
	public boolean updateOpportunityDetails(CrmOpportunityDetails details) {
		Session session = HibernateSessionFactory.getSessionFactory(details.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(details);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteOpportunityDetails(CrmOpportunityDetails details) {
		Session session = HibernateSessionFactory.getSessionFactory(details.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(details);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public CrmOpportunityDetails findOpportunityDetailsById(int opDetailsId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmOpportunityDetails.class);
			criteria.add(Restrictions.eq("opDetailsId", opDetailsId));
			criteria.addOrder(Order.asc("lineNo"));
			return (CrmOpportunityDetails)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunityDetails> listOpportunityDetails(MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmOpportunityDetails.class);
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
	public Map<String, Object> startUpPage(MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LOCATION", listAllLocations(dataSource));
		map.put("UOM", listAllUoms(dataSource));
		map.put("CLASSES", listAllClasses(dataSource));
		map.put("ITEMS", listAllItems(dataSource));
		return map;
	}
	
	@SuppressWarnings("unchecked")
	private List<AmeLocation> listAllLocations(MeDataSource dataSource){
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(AmeLocation.class);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<AmeUom> listAllUoms(MeDataSource dataSource){
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(AmeUom.class);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		
	}
	
	@SuppressWarnings("unchecked")
	private List<AmeItem> listAllItems(MeDataSource dataSource){
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(AmeItem.class);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<AmeClass> listAllClasses(MeDataSource dataSource){
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(AmeClass.class);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunityDetails> listOpportunityDetailsRelatedToOpportunity(String opId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		
		try {
			Criteria criteria = session.createCriteria(CrmOpportunityDetails.class);
			criteria.add(Restrictions.eq("opId", opId));
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
	public boolean deleteOpportunityDetails(String opId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_opportunity_detail WHERE OP_ID = :opId");
			query.setParameter("opId", opId);
			query.executeUpdate();
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
		}
		return false;
	}

}
