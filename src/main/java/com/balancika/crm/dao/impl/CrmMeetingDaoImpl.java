package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmMeetingDao;
import com.balancika.crm.model.Company;
import com.balancika.crm.model.CrmMeeting;
import com.balancika.crm.utilities.DateTimeOperation;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmMeetingDaoImpl extends CrmIdGenerator implements CrmMeetingDao {

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private Company config;

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmMeeting> listMeetings() {
		Session session = sessionFactory.openSession();
		try{
		/*transactionManager.getSessionFactory().openSession();*/
		SQLQuery query = session.createSQLQuery("CALL listCrmMeetings()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public boolean insertMeeting(CrmMeeting meeting) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			DateTimeOperation toLocalDateTime = new DateTimeOperation();
			meeting.setMeetingId(IdAutoGenerator("AC_ME"));
			meeting.setMeetingStartDate(toLocalDateTime.convertStringToLocalDateTime(meeting.getStartDate()));
			meeting.setMeetingEndDate(toLocalDateTime.convertStringToLocalDateTime(meeting.getEndDate()));
			meeting.setMeetingCreateDate(LocalDateTime.now());
			session.save(meeting);
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
	public boolean updateMeeting(CrmMeeting meeting) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			DateTimeOperation toLocalDateTime = new DateTimeOperation();
			meeting.setMeetingStartDate(toLocalDateTime.convertStringToLocalDateTime(meeting.getStartDate()));
			meeting.setMeetingEndDate(toLocalDateTime.convertStringToLocalDateTime(meeting.getEndDate()));
			session.update(meeting);
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
	public boolean deleteMeeting(String meetingId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmMeeting meeting = new CrmMeeting();
			meeting.setMeetingId(meetingId);
			session.delete(meeting);
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
	public Object findMeetingById(String meetingId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL findCrmMeetingById(:meetingId)");
		query.setParameter("meetingId", meetingId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

	@Override
	public CrmMeeting findMeetingDetailsById(String meetingId) {
		Criteria criteria = transactionManager.getSessionFactory().getCurrentSession().createCriteria(CrmMeeting.class);
		criteria.add(Restrictions.eq("meetingId", meetingId));
		return (CrmMeeting) criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmMeeting> listMeetingsRelatedToLead(String leadId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listMeetingsRelatedToLead(:leadId)");
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
	public List<CrmMeeting> listMeetingsRelatedToOpportunity(String opId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listMeetingsRelatedToOpportunity(:opId)");
				query.setParameter("opId", opId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmMeeting> listMeetingsRelatedToModule(String moduleId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listMeetingsRelatedToModule(:moduleId)");
				query.setParameter("moduleId", moduleId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
