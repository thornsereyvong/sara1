package com.balancika.crm.dao.impl;

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
import com.balancika.crm.dao.CrmCallDao;
import com.balancika.crm.dao.CrmEventDao;
import com.balancika.crm.dao.CrmLeadDao;
import com.balancika.crm.dao.CrmMeetingDao;
import com.balancika.crm.dao.CrmNoteDao;
import com.balancika.crm.dao.CrmTaskDao;
import com.balancika.crm.model.CrmLead;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;

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
			lead.setCreateDate(new Date());
			session.save(lead);
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
			session.delete(lead);
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
	public Map<String,Object> viewActivitiesOfLeadById(String leadId, MeDataSource dataSource) {
		Map<String, Object> leadMap = new HashMap<String, Object>();
		leadMap.put("CALLS", callDao.listCallsRelatedToLead(leadId, dataSource));
		leadMap.put("METTINGS", meetingDao.listMeetingsRelatedToLead(leadId, dataSource));
		leadMap.put("EVENTS", eventDao.listEventsRelatedToLead(leadId, dataSource));
		leadMap.put("TASKS", taskDao.listTasksRelatedToLead(leadId, dataSource));
		leadMap.put("NOTES", noteDao.listNoteRelatedToLead(leadId,dataSource));
		leadMap.put("LEAD", findLeadById(leadId, dataSource));
		leadMap.put("ALL_ACTIVITIES", listActivitesRelatedToLead(leadId,dataSource));
		return leadMap;
	}
	
	@SuppressWarnings("unchecked")
	private List<Object> listActivitesRelatedToLead(String leadId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listAllActivitiesRelatedToLead(:leadId)");
				query.setParameter("leadId", leadId);
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
}
