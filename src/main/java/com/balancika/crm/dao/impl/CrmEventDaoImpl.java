package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

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

@Repository
public class CrmEventDaoImpl extends CrmIdGenerator implements CrmEventDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertEvent(CrmEvent event) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			event.setEvId(IdAutoGenerator("AC_EV"));
			ConvertStringToLocalDateTime toLocalDateTime = new ConvertStringToLocalDateTime();
			event.setEvStartDate(toLocalDateTime.convertStringToLocalDateTime(event.getStartDate()));
			event.setEvEndDate(toLocalDateTime.convertStringToLocalDateTime(event.getEndDate()));
			event.setEvCreateDate(LocalDateTime.now());
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
	public boolean updateEvent(CrmEvent event) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			ConvertStringToLocalDateTime toLocalDateTime = new ConvertStringToLocalDateTime();
			event.setEvStartDate(toLocalDateTime.convertStringToLocalDateTime(event.getStartDate()));
			event.setEvEndDate(toLocalDateTime.convertStringToLocalDateTime(event.getEndDate()));
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
