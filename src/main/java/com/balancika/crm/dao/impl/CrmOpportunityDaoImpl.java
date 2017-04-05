package com.balancika.crm.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmOpportunityDao;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.CrmOpportunityDetails;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityDetailsService;
import com.balancika.crm.utilities.AppUtilities;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DBConnection;

@Repository
public class CrmOpportunityDaoImpl extends CrmIdGenerator implements CrmOpportunityDao{
	
	@Autowired
	private CrmOpportunityDetailsService detailsService;
	
	private SessionFactory sessionFactory;
	
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean isInsertOpportunity(CrmOpportunity opportunity) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(opportunity.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			opportunity.setOpId(IdAutoGenerator("OP", opportunity.getMeDataSource()));
			opportunity.setOpCreateDate(new Date());
			session.save(opportunity);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean isUpdateOpportunity(CrmOpportunity opportunity) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(opportunity.getMeDataSource()));
		Session session = getSessionFactory().openSession();
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
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean isDeleteOpportunity(CrmOpportunity opportunity) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(opportunity.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("CALL crmDeleteModuleRelatedToOpportunity(:oppId)");
			query.setParameter("oppId", opportunity.getOpId());
			if(query.executeUpdate() > 0){
				session.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public Object findOpportunityById(String opId, MeDataSource  dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
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
			sessionFactory.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunity> listOpportunities(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmOpportunities()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@Override
	public CrmOpportunity findOpportunityDetailsById(String opId,MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			return (CrmOpportunity)session.get(CrmOpportunity.class, opId);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listOpportunitiesWithSpecificUser(String username, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
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
			sessionFactory.close();
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> listContactsRelatedToOpportuntiy(String opId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
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
			sessionFactory.close();
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> listQuotationsRelatedToOpportuntiy(String opId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
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
			sessionFactory.close();
		}
		return null;
	}
	
	@Override
	@SuppressWarnings("unchecked")
	public List<Object> listSaleOrdersRelatedToOpportuntiy(String opId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session  session = getSessionFactory().openSession();
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
			sessionFactory.close();
		}
		return null;
	}
	
	private double generateDisInvByItem(double netAmt, double persent){
		if(netAmt == 0 || persent == 0){
			return 0;
		}
		return (netAmt * persent / 100);
	}

	@Override
	public boolean updateCustomFieldsOfOpprotunity(CrmOpportunity opp) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(opp.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try{
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
					details.setMeDataSource(opp.getMeDataSource());
					detailsService.insertOpportunityDetails(details);
				}
			}
			session.merge(opportunity);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public Map<String, Object> viewOpportunityById(String opId, String userId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try(Connection con = DBConnection.getConnection(dataSource)){
			CallableStatement cs= con.prepareCall("{call crmViewOpportunityById(?,?)}");
			cs.setString(1, opId);
			cs.setString(2, userId);
			boolean isResultSet = cs.execute();
			int rsCount = 0;
			String[] key = {
					"TASKS","TASK_STATUS","EVENTS","EVENT_LOCATION","CALLS","CALL_STATUS",
					"MEETINGS","MEETING_STATUS","NOTES","ASSIGN_TO","TAG_TO","LEAD_SOURCE","ALL_CONTACTS",
					"CAMPAIGNS","PRICE_CODE","CUSTOMERS","OPP_STAGES","OPP_TYPES","LOCATION","UOM","CLASSES",
					"ITEMS","QUOTATIONS","SALE_ORDERS","CONTACTS","ALL_QUOTATIONS","ALL_SALE_ORDERS","OP_PROJECT"
					}; // 
			while(isResultSet){
				ResultSet rs = cs.getResultSet();
				map.put(key[rsCount], AppUtilities.aliasToMaps(rs));
				rs.close();
				isResultSet = cs.getMoreResults();
				rsCount++;
			}
			CallableStatement cst = con.prepareCall("{call findCrmOpportunityById(?)}");
			cst.setString(1, opId);
			map.put("OPPORTUNITY", AppUtilities.aliasToSingleMap(cst.executeQuery()));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
