package com.balancika.crm.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCallDao;
import com.balancika.crm.dao.CrmEventDao;
import com.balancika.crm.dao.CrmLeadDao;
import com.balancika.crm.dao.CrmMeetingDao;
import com.balancika.crm.dao.CrmNoteDao;
import com.balancika.crm.dao.CrmTaskDao;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.AppUtilities;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DBConnection;

@Repository
public class CrmLeadDaoImpl extends CrmIdGenerator implements CrmLeadDao {

	@Autowired
	private CrmNoteDao noteDao;
	
	@Autowired
	private CrmEventDao eventDao;
	
	@Autowired
	private CrmCallDao callDao;
	
	@Autowired
	private CrmTaskDao taskDao; 
	
	@Autowired
	private CrmMeetingDao meetingDao;

	
	private SessionFactory sessionFactory;
	
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertLead(CrmLead lead) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(lead.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			lead.setLeadID(IdAutoGenerator("LE", lead.getMeDataSource()));
			lead.setCreateDate(LocalDateTime.now());
			session.save(lead);
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
	public boolean updateLead(CrmLead lead) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(lead.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(lead);
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
	public boolean deleteLead(CrmLead lead) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(lead.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("CALL crmDeleteModuleRelatedToLead(:leadId)");
			query.setParameter("leadId", lead.getLeadID());
			query.executeUpdate();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLead> getAllLead(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmLeads()");
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
	public Object findLeadById(String leadID, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session =  getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findCrmLeadById(:leadID)");
			query.setParameter("leadID", leadID);
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

	@Override
	public CrmLead findLeadDetailById(String leadID, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session =  new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			return (CrmLead)session.get(CrmLead.class, leadID);
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
	public List<CrmLead> getLeadBySpecificUser(String username, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listLeadWithSpecificUser(:username)");
			query.setParameter("username", username);
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
	public boolean updateLeadStatusToConverted(String leadID, String custId, String opId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL updateLeadStatusToConverted(:leadId, :custId, :opId)");
			query.setParameter("leadId", leadID);
			query.setParameter("custId", custId);
			query.setParameter("opId", opId);
			if(query.executeUpdate() > 0){
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public Map<String, Object> viewLeadById(String leadId, String userId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		try(Connection con = DBConnection.getConnection(dataSource)){
			CallableStatement cs = con.prepareCall("{call crmViewLeadById(?,?)}");
			cs.setString(1, leadId);
			cs.setString(2, userId);
			boolean isResultSet = cs.execute();
			int rsCount = 0;
			String[] key = {"TASKS","TASK_STATUS","EVENTS","EVENT_LOCATION","CALLS","CALL_STATUS","METTINGS","MEETING_STATUS","CONTACTS","NOTES","ASSIGN_TO","TAG_TO","CAMPAIGN","INDUSTRY","LEAD_STATUS","LEAD_SOURCE"}; // 
			while(isResultSet){
				ResultSet rs = cs.getResultSet();
				map.put(key[rsCount], AppUtilities.aliasToMaps(rs));
				rs.close();
				isResultSet = cs.getMoreResults();
				rsCount++;
			}
			
			CallableStatement cst = con.prepareCall("{call findCrmLeadById(?)}");
			cst.setString(1, leadId);
			map.put("LEAD", AppUtilities.aliasToSingleMap(cst.executeQuery()));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> convertLeadStartup(String leadId, String userId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		try(Connection con = DBConnection.getConnection(dataSource)){
			CallableStatement cs = con.prepareCall("{call crmConvertLeadStartup(?,?)}");
			cs.setString(1, leadId);
			cs.setString(2, userId);
			boolean isResultSet = cs.execute();
			int rsCount = 0;
			String[] key = {"CONTACT","ASSIGN_TO","CAMPAIGN","INDUSTRY","LEAD_STATUS","LEAD_SOURCE","GROUP","PRICE_CODE","CUSTOMER","CUSTOMER_TYPE","OPP_STAGES","OPP_TYPES"}; // 
			while(isResultSet){
				ResultSet rs = cs.getResultSet();
				map.put(key[rsCount], AppUtilities.aliasToMaps(rs));
				rs.close();
				isResultSet = cs.getMoreResults();
				rsCount++;
			}
			CallableStatement cst = con.prepareCall("{call findCrmLeadById(?)}");
			cst.setString(1, leadId);
			map.put("LEAD", AppUtilities.aliasToSingleMap(cst.executeQuery()));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> editLeadStartup(String leadId, String userId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		try(Connection con = DBConnection.getConnection(dataSource)){
			CallableStatement cs = con.prepareCall("{call crmEditOrAddLeadStartup(?)}");
			cs.setString(1, userId);
			boolean isResultSet = cs.execute();
			int rsCount = 0;
			String[] key = {"CAMPAIGN","INDUSTRY","LEAD_STATUS","LEAD_SOURCE","ASSIGN_TO"}; // 
			while(isResultSet){
				ResultSet rs = cs.getResultSet();
				map.put(key[rsCount], AppUtilities.aliasToMaps(rs));
				rs.close();
				isResultSet = cs.getMoreResults();
				rsCount++;
			}
			CallableStatement cst = con.prepareCall("{call findCrmLeadById(?)}");
			cst.setString(1, leadId);
			map.put("LEAD", AppUtilities.aliasToSingleMap(cst.executeQuery()));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public Map<String, Object> createLeadStartup(String userId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		try(Connection con = DBConnection.getConnection(dataSource)){
			CallableStatement cs = con.prepareCall("{call crmEditOrAddLeadStartup(?)}");
			cs.setString(1, userId);
			boolean isResultSet = cs.execute();
			int rsCount = 0;
			String[] key = {"CAMPAIGN","INDUSTRY","LEAD_STATUS","LEAD_SOURCE","ASSIGN_TO"}; // 
			while(isResultSet){
				ResultSet rs = cs.getResultSet();
				map.put(key[rsCount], AppUtilities.aliasToMaps(rs));
				rs.close();
				isResultSet = cs.getMoreResults();
				rsCount++;
			}
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
