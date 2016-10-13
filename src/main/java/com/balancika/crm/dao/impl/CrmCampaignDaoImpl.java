package com.balancika.crm.dao.impl;

import java.util.Date;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCampaignDao;
import com.balancika.crm.model.CrmCampaign;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmCampaignDaoImpl extends CrmIdGenerator implements CrmCampaignDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertCampaign(CrmCampaign cmp) {
		Session session = transactionManager.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			System.err.println(cmp.getCampName());
			cmp.setCampID(IdAutoGenerator("CA"));
			cmp.setCreatedDate(new Date());
			session.save(cmp);
			session.getTransaction().commit();
			return true;
		} catch(HibernateException e){
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally{
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCampaign> listCampaigns() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listCrmCampaigns()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public boolean updateCampaign(CrmCampaign cmp) {
		Session session = transactionManager.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			session.update(cmp);
			session.getTransaction().commit();
			return true;
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public String deleteCampaign(String campID) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_camp WHERE CA_ID = :campID");  
			query.setParameter("campID", campID);
			if(query.executeUpdate() > 0){
				session.getTransaction().commit();
				return "OK";
			}
		} catch (ConstraintViolationException ex) {
			ex.printStackTrace();
			session.getTransaction().rollback();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} finally{
			session.close();
		}
		return "FAILED";
	}

	@Override
	public Object findCampaignById(String campID) {
		
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL findCrmCampaignById(:campID)");
		query.setParameter("campID", campID);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

	@Override
	public boolean isCampaignNameExist(String campName) {
		Integer countRow = 0;
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmCampaign.class);
		criteria.add(Restrictions.eq("campName", campName));
		countRow = ((Number)criteria.setProjection(Projections.count("campName")).uniqueResult()).intValue();
		if(countRow > 0){
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listCampaignIsNotEqual(String campID) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmParentCampaigns(:campID)");
			query.setParameter("campID", campID);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public CrmCampaign findCampaignDetailsById(String campID) {
		return (CrmCampaign)transactionManager.getSessionFactory().getCurrentSession().get(CrmCampaign.class, campID);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listCampaignParents() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(CrmCampaign.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("campID"), "campID")
																 .add(Projections.property("campName"), "campName"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			criteria.add(Restrictions.isNull("parent"));
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listIdAndNameOfCompaign() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(CrmCampaign.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("campID"), "campID")
																 .add(Projections.property("campName"), "campName"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunity> getOpportunitiesRelatedToCampaign(String campID) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(CrmOpportunity.class, "op")
					.createAlias("op.opCampaign", "camp")
					.createAlias("op.customer", "cust");
			criteria.add(Restrictions.eq("camp.campID", campID));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("opId"), "opId")
					.add(Projections.property("opName"), "opName")
					.add(Projections.property("opAmount"), "opAmount")
					.add(Projections.property("opStageId"), "opStageId")
					.add(Projections.property("opCloseDate"), "opCloseDate")
					.add(Projections.property("cust.custID"), "custID")
					.add(Projections.property("cust.custName"), "custName"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
