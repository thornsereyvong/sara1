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
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmContactDao;
import com.balancika.crm.dao.CrmOpportunityDao;
import com.balancika.crm.dao.QuoteDao;
import com.balancika.crm.dao.SaleOrderDao;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.CrmOpportunityDetails;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityDetailsService;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmOpportunityDaoImpl extends CrmIdGenerator implements CrmOpportunityDao{

	@Autowired
	private CrmContactDao contactDao;
	
	@Autowired
	private SaleOrderDao saleOrderDao;
	
	@Autowired
	private QuoteDao quoteDao;
	
	@Autowired
	private CrmOpportunityDetailsService detailsService;
	
	@Override
	public boolean isInsertOpportunity(CrmOpportunity opportunity) {
		Session session = new HibernateSessionFactory().getSessionFactory(opportunity.getMeDataSource()).openSession();
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
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean isUpdateOpportunity(CrmOpportunity opportunity) {
		Session session = new HibernateSessionFactory().getSessionFactory(opportunity.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(opportunity);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean isDeleteOpportunity(CrmOpportunity opportunity) {
		Session session = new HibernateSessionFactory().getSessionFactory(opportunity.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(opportunity);
			detailsService.deleteOpportunityDetails(opportunity.getOpId(), opportunity.getMeDataSource());
			this.deleteOpportunityQuote(opportunity.getOpId(), opportunity.getMeDataSource());
			this.deleteOpportunitySaleOrder(opportunity.getOpId(), opportunity.getMeDataSource());
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
	public Object findOpportunityById(String opId, MeDataSource  dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findCrmOpportunityById(:opId)");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.uniqueResult();
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
	public List<CrmOpportunity> listOpportunities(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmOpportunities()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public CrmOpportunity findOpportunityDetailsById(String opId,MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			return (CrmOpportunity)session.get(CrmOpportunity.class, opId);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listOpportunitiesWithSpecificUser(String username, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listOpportunityWithSpecificUser(:username)");
			query.setParameter("username", username);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> listContactsRelatedToOpportuntiy(String opId, MeDataSource dataSource){
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listContactsRelatedToOpportunity(:opId)");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> listQuotationsRelatedToOpportuntiy(String opId, MeDataSource dataSource){
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listQuotationRelatedToOpportunity(:opId)");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> listSaleOrdersRelatedToOpportuntiy(String opId, MeDataSource dataSource){
		Session  session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listSaleOrdersRelatedToOpportunity(:opId)");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public Map<String, Object> listInformationRelateToOpportunity(String opId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("QUOTATIONS", listQuotationsRelatedToOpportuntiy(opId, dataSource));
		map.put("SALE_ORDERS", listSaleOrdersRelatedToOpportuntiy(opId, dataSource));
		map.put("CONTACTS", listContactsRelatedToOpportuntiy(opId, dataSource));
		map.put("ALL_CONTACTS", contactDao.listSomeFieldsOfContact(dataSource));
		map.put("ALL_SALE_ORDERS", saleOrderDao.listSomeFieldsOfSaleOrder(opId, dataSource));
		map.put("ALL_QUOTATIONS", quoteDao.listCustomFieldOfQuotes(opId, dataSource));
		return map;
	}
	
	private void deleteOpportunityQuote(String opId, MeDataSource dataSource){
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
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
		} finally {
			session.clear();
			session.close();
		}
	}
	
	private void deleteOpportunitySaleOrder(String opId, MeDataSource dataSource){
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
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
		} finally {
			session.clear();
			session.close();
		}
	}
	
	private double generateDisInvByItem(double netAmt, double persent){
		if(netAmt == 0 || persent == 0){
			return 0;
		}
		return (netAmt * persent / 100);
	}

	@Override
	public boolean updateCustomFieldsOfOpprotunity(CrmOpportunity opp) {
		Session session = new HibernateSessionFactory().getSessionFactory(opp.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			CrmOpportunity opportunity = (CrmOpportunity) session.load(CrmOpportunity.class, opp.getOpId());
			opportunity.setTotalAmount(opp.getTotalAmount());
			opportunity.setTotalDis(opp.getTotalDis());
			opportunity.setTotalSTax(opp.getTotalSTax());
			opportunity.setTotalVTax(opp.getTotalVTax());
			opportunity.setDisInvDol(opp.getDisInvDol());
			opportunity.setDisInvPer(opp.getDisInvPer());
			opportunity.setOpAmount(opp.getOpAmount());
			if(opp.getDetails() != null){
				detailsService.deleteOpportunityDetails(opp.getOpId(), opp.getMeDataSource());
				for(CrmOpportunityDetails details : opp.getDetails()){
					details.setOpId(opp.getOpId());
					details.setDisInv(generateDisInvByItem(details.getNetTotalAmt(), opportunity.getDisInvPer()));
					detailsService.insertOpportunityDetails(details);
				}
			}
			session.merge(opportunity);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.clear();
			session.close();
		}
		return false;
	}
}
