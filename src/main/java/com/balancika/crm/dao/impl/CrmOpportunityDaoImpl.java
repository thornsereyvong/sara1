package com.balancika.crm.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmContactDao;
import com.balancika.crm.dao.CrmOpportunityDao;
import com.balancika.crm.dao.QuoteDao;
import com.balancika.crm.dao.SaleOrderDao;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.CrmOpportunityDetails;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmOpportunityDaoImpl extends CrmIdGenerator implements CrmOpportunityDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Autowired
	private CrmContactDao contactDao;
	
	@Autowired
	private SaleOrderDao saleOrderDao;
	
	@Autowired
	private QuoteDao quoteDao;
	
	@Override
	public boolean isInsertOpportunity(CrmOpportunity opportunity) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			opportunity.setOpId(IdAutoGenerator("OP"));
			opportunity.setOpCreateDate(new Date());
			session.save(opportunity);
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
	public boolean isUpdateOpportunity(CrmOpportunity opportunity) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			if(opportunity.getDetails() != null){
				for(CrmOpportunityDetails details : opportunity.getDetails()){
					details.setDisInv(generateDisInvByItem(details.getNetTotalAmt(), opportunity.getDisInvPer()));
				}
			}
			session.update(opportunity);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean isDeleteOpportunity(String opId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmOpportunity opportunity = new CrmOpportunity();
			opportunity.setOpId(opId);
			session.delete(opportunity);
			this.deleteOpportunityQuote(opId);
			this.deleteOpportunitySaleOrder(opId);
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
	public Object findOpportunityById(String opId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL findCrmOpportunityById(:opId)");
		query.setParameter("opId", opId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunity> listOpportunities() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listCrmOpportunities()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public CrmOpportunity findOpportunityDetailsById(String opId) {
		return (CrmOpportunity)transactionManager.getSessionFactory().getCurrentSession().get(CrmOpportunity.class, opId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listOpportunitiesWithSpecificUser(String username) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listOpportunityWithSpecificUser(:username)");
			query.setParameter("username", username);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> listContactsRelatedToOpportuntiy(String opId){
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listContactsRelatedToOpportunity(:opId)");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> listQuotationsRelatedToOpportuntiy(String opId){
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listQuotationRelatedToOpportunity(:opId)");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> listSaleOrdersRelatedToOpportuntiy(String opId){
		Session  session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listSaleOrdersRelatedToOpportunity(:opId)");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> listInformationRelateToOpportunity(String opId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("QUOTATIONS", listQuotationsRelatedToOpportuntiy(opId));
		map.put("SALE_ORDERS", listSaleOrdersRelatedToOpportuntiy(opId));
		map.put("CONTACTS", listContactsRelatedToOpportuntiy(opId));
		map.put("ALL_CONTACTS", contactDao.listSomeFieldsOfContact());
		map.put("ALL_SALE_ORDERS", saleOrderDao.listSomeFieldsOfSaleOrder(opId));
		map.put("ALL_QUOTATIONS", quoteDao.listCustomFieldOfQuotes(opId));
		return map;
	}
	
	private void deleteOpportunityQuote(String opId){
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_opportunity_quote WHERE OP_ID = :opId ;");
			query.setParameter("opId", opId);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.close();
		}
	}
	
	private void deleteOpportunitySaleOrder(String opId){
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_opportunity_saleorder WHERE OP_ID = :opId ;");
			query.setParameter("opId", opId);
			query.executeUpdate();
			session.getTransaction().commit();
			session.close();
		} catch (HibernateException e) {
			e.printStackTrace();
			session.close();
		}
	}
	
	private double generateDisInvByItem(double netAmt, double persent){
		if(netAmt == 0 || persent == 0){
			return 0;
		}
		return (netAmt * persent / 100);
	}
}
