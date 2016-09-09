package com.balancika.crm.dao.impl;

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

import com.balancika.crm.dao.CrmCallDao;
import com.balancika.crm.dao.CrmEventDao;
import com.balancika.crm.dao.CrmLeadDao;
import com.balancika.crm.dao.CrmMeetingDao;
import com.balancika.crm.dao.CrmNoteDao;
import com.balancika.crm.dao.CrmTaskDao;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class CrmLeadDaoImpl extends CrmIdGenerator implements CrmLeadDao {

	@Autowired
	private HibernateTransactionManager transactionManager;
	
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

	@Override
	public boolean insertLead(CrmLead lead) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			lead.setLeadID(IdAutoGenerator("LE"));
			session.save(lead);
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
	public boolean updateLead(CrmLead lead) {

		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(lead);
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
	public boolean deleteLead(String leadID) {
		
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmLead lead = new CrmLead();
			lead.setLeadID(leadID);
			session.delete(lead);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLead> getAllLead() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listCrmLeads()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public Object findLeadById(String leadID) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL findCrmLeadById(:leadID)");
		query.setParameter("leadID", leadID);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

	@Override
	public CrmLead findLeadDetailById(String leadID) {
		return (CrmLead)transactionManager.getSessionFactory().getCurrentSession().get(CrmLead.class, leadID);
	}

	@Override
	public boolean convertLead(String json) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> map = mapper.readValue(json, new TypeReference<Map<String, Object>>() {
			});
			
			SQLQuery query = session.createSQLQuery("CALL convertCrmLead("
					+ ":conFirstname, :conLastname, :conPhone, :conMobile, "
					+ ":conEmial, :conTitle, :conDepartment, :conNo,"
					+ ":conStreet, :conVillage, :conCommune, :conDistrict, "
					+ ":conCity, :conState, :conCountry, :conLeadSoruce, "
					+ ":conCreateBy, :conCreateDate, :custName, :custTel1, "
					+ ":custTel2, :custFax, :custEmail, :custWebsite, :custAddress,"
					+ ":facebook, :line, :viber, :whatApp, :industID, :accountTypeID,"
					+ ":leadID, :leadStatusId)");
					
					query.setParameter("conFirstname", map.get("conFirstname"));
					query.setParameter("conLastname", map.get("conLastname"));
					query.setParameter("conPhone", map.get("conPhone"));
					query.setParameter("conMobile", map.get("conMobile"));
					query.setParameter("conEmial", map.get("conEmial"));
					query.setParameter("conTitle", map.get("conTitle"));
					query.setParameter("conDepartment", map.get("conDepartment"));
					query.setParameter("conNo", map.get("conNo"));
					query.setParameter("conStreet", map.get("conStreet"));
					query.setParameter("conVillage", map.get("conVillage"));
					query.setParameter("conCommune", map.get("conCommune"));
					query.setParameter("conDistrict", map.get("conDistrict"));
					query.setParameter("conCity", map.get("conCity"));
					query.setParameter("conState", map.get("conState"));
					query.setParameter("conCountry", map.get("conCountry"));
					query.setParameter("conLeadSoruce", map.get("conLeadSoruce"));
					query.setParameter("conCreateBy", map.get("conCreateBy"));
					query.setParameter("conCreateDate", map.get("conCreateDate"));
					query.setParameter("custName", map.get("custName"));
					query.setParameter("custTel1", map.get("custTel1"));
					query.setParameter("custTel2", map.get("custTel2"));
					query.setParameter("custFax", map.get("custFax"));
					query.setParameter("custEmail", map.get("custEmail"));
					query.setParameter("custWebsite", map.get("custWebsite"));
					query.setParameter("custAddress", map.get("custAddress"));
					query.setParameter("facebook", map.get("facebook"));
					query.setParameter("line", map.get("line"));
					query.setParameter("viber", map.get("viber"));
					query.setParameter("whatApp", map.get("whatApp"));
					query.setParameter("industID", map.get("industID"));
					query.setParameter("accountTypeID", map.get("accountTypeID"));
					query.setParameter("leadID", map.get("leadID"));
					query.setParameter("leadStatusId", map.get("leadStatusId"));
			
					if(query.executeUpdate() > 0){
						return true;
					}
					
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLead> getLeadBySpecificUser(String username) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listLeadWithSpecificUser(:username)");
		query.setParameter("username", username);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public Map<String,Object> viewActivitiesOfLeadById(String leadId) {
		Map<String, Object> leadMap = new HashMap<String, Object>();
		leadMap.put("CALLS", callDao.listCallsRelatedToLead(leadId));
		leadMap.put("METTINGS", meetingDao.listTasksRelatedToLead(leadId));
		leadMap.put("EVENTS", eventDao.listEventsRelatedToLead(leadId));
		leadMap.put("TASKS", taskDao.listTasksRelatedToLead(leadId));
		leadMap.put("NOTES", noteDao.listNoteRelatedToLead(leadId));
		leadMap.put("LEAD", findLeadById(leadId));
		leadMap.put("ALL_ACTIVITIES", listActivitesRelatedToLead(leadId));
		return leadMap;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> listActivitesRelatedToLead(String leadId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listAllActivitiesRelatedToLead(:leadId)");
				query.setParameter("leadId", leadId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

}
