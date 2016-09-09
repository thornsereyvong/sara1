package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmMeetingDao;
import com.balancika.crm.model.CrmMeeting;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmMeetingDaoImpl extends CrmIdGenerator implements CrmMeetingDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmMeeting> listMeetings() {
		Session session = transactionManager.getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery("CALL listCrmMeetings()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public boolean insertMeeting(CrmMeeting meeting) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			meeting.setMeetingId(IdAutoGenerator("AC_ME"));
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
			session.update(meeting);
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
	public List<CrmMeeting> listTasksRelatedToLead(String leadId) {
		Session session = transactionManager.getSessionFactory().openSession();
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
	public List<CrmMeeting> listTasksRelatedToOpportunity(String opId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listMeetingsRelatedToLead(:opId)");
				query.setParameter("opId", opId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
}
