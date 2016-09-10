package com.balancika.crm.dao.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmEventDao;
import com.balancika.crm.model.CrmEvent;
import com.balancika.crm.utilities.ConvertStringToLocalDateTime;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Repository
public class CrmEventDaoImpl extends CrmIdGenerator implements CrmEventDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertEvent(String json) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> eventMap = new HashMap<String, Object>();
			CrmEvent event = new CrmEvent();
			ConvertStringToLocalDateTime toLocalDateTime = new ConvertStringToLocalDateTime();
			try {
				eventMap = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
				event.setEvId(IdAutoGenerator("AC_EV"));
				event.setEvName(eventMap.get("evName").toString());
				if(eventMap.get("evLocation") == null){
					event.setEvlocation(null);
				}else{
					event.setEvlocation(eventMap.get("evLocation").toString());
				}
				event.setEvBudget(Double.parseDouble(eventMap.get("evBudget").toString()));
				event.setEvDes(eventMap.get("evDes").toString());
				event.setEvCreateBy(eventMap.get("evCreateBy").toString());
				event.setEvDuration(eventMap.get("evDuration").toString());
				event.setEvStartDate(toLocalDateTime.convertStringToLocalDateTime(eventMap.get("evStartDate").toString()));
				event.setEvEndDate(toLocalDateTime.convertStringToLocalDateTime(eventMap.get("evEndDate").toString()));
				if(eventMap.get("assignTo") == null){
					event.setAssignTo(null);
				}else{
					event.setAssignTo(eventMap.get("assignTo").toString());
				}
				event.setEvCreateDate(LocalDateTime.now());
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.save(event);
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
	public boolean updateEvent(String json) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			ObjectMapper mapper = new ObjectMapper();
			Map<String, Object> eventMap = new HashMap<String, Object>();
			CrmEvent event = new CrmEvent();
			ConvertStringToLocalDateTime toLocalDateTime = new ConvertStringToLocalDateTime();
			try {
				eventMap = mapper.readValue(json, new TypeReference<Map<String, Object>>() {});
				event.setEvId(IdAutoGenerator("AC_EV"));
				event.setEvName(eventMap.get("evName").toString());
				if(eventMap.get("evLocation") == null){
					event.setEvlocation(null);
				}else{
					event.setEvlocation(eventMap.get("evLocation").toString());
				}
				event.setEvBudget(Double.parseDouble(eventMap.get("evBudget").toString()));
				event.setEvDes(eventMap.get("evDes").toString());
				event.setEvDuration(eventMap.get("evDuration").toString());
				event.setEvStartDate(toLocalDateTime.convertStringToLocalDateTime(eventMap.get("evStartDate").toString()));
				event.setEvEndDate(toLocalDateTime.convertStringToLocalDateTime(eventMap.get("evEndDate").toString()));
				if(eventMap.get("assignTo") == null){
					event.setAssignTo(null);
				}else{
					event.setAssignTo(eventMap.get("assignTo").toString());
				}
				event.setEvCreateDate(LocalDateTime.now());
				event.setEvModifiedBy(eventMap.get("evModifiedBy").toString());
			} catch (JsonParseException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			session.update(event);
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
	public boolean deleteEnvent(String evId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("DELETE FROM crm_event WHERE EV_ID = :evId");
		query.setParameter("evId", evId);
		if (query.executeUpdate() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmEvent> listEvents() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listCrmEvents()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public Object findEventById(String evId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL findCrmEventById(:evId)");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		query.setParameter("evId", evId);
		return query.uniqueResult();
	}

	@Override
	public CrmEvent findEventDetailsById(String evId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmEvent.class);
		criteria.add(Restrictions.eq("evId", evId));
		return (CrmEvent) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmEvent> listEventsRelatedToLead(String leadId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listEventsRelatedToLead(:leadId)");
				query.setParameter("leadId", leadId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmEvent> listEventsRelatedToOpportunity(String opId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listEventsRelatedOpportuntiy(:opId)");
				query.setParameter("opId", opId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

}
