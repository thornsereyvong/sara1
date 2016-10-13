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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmOpportunityDetailsDao;
import com.balancika.crm.model.AmeClass;
import com.balancika.crm.model.AmeItem;
import com.balancika.crm.model.AmeLocation;
import com.balancika.crm.model.AmeUom;
import com.balancika.crm.model.CrmOpportunityDetails;

@Repository
public class CrmOpportunityDetailsDaoImpl implements CrmOpportunityDetailsDao{
	
	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertOpportunityDetails(CrmOpportunityDetails details) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(details);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}
	
	/*private double generateDisInvByItem(double netAmt, double persent){
		if(netAmt == 0 || persent == 0){
			return 0;
		}
		return (netAmt * persent / 100);
	}*/

	@Override
	public boolean updateOpportunityDetails(CrmOpportunityDetails details) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(details);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteOpportunityDetails(int opDetailsId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmOpportunityDetails details = new CrmOpportunityDetails();
			details.setOpDetailsId(opDetailsId);
			session.delete(details);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@Override
	public CrmOpportunityDetails findOpportunityDetailsById(int opDetailsId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmOpportunityDetails.class);
		criteria.add(Restrictions.eq("opDetailsId", opDetailsId));
		criteria.addOrder(Order.asc("lineNo"));
		return (CrmOpportunityDetails)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunityDetails> listOpportunityDetails() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmOpportunityDetails.class);
		return criteria.list();
	}

	@Override
	public Map<String, Object> startUpPage() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("LOCATION", listAllLocations());
		map.put("UOM", listAllUoms());
		map.put("CLASSES", listAllClasses());
		map.put("ITEMS", listAllItems());
		return map;
	}
	
	@SuppressWarnings("unchecked")
	private List<AmeLocation> listAllLocations(){
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(AmeLocation.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	private List<AmeLocation> listAllUoms(){
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(AmeUom.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	private List<AmeLocation> listAllItems(){
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(AmeItem.class);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	private List<AmeLocation> listAllClasses(){
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(AmeClass.class);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunityDetails> listOpportunityDetailsRelatedToOpportunity(String opId) {

		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmOpportunityDetails.class);
		criteria.add(Restrictions.eq("opId", opId));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public boolean deleteOpportunityDetails(String opId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_opportunity_detail WHERE OP_ID = :opId");
			query.setParameter("opId", opId);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

}
