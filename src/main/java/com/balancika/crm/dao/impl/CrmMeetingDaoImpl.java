package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmMeetingDao;
import com.balancika.crm.model.Company;
import com.balancika.crm.model.CrmMeeting;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.DateTimeOperation;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmMeetingDaoImpl extends CrmIdGenerator implements CrmMeetingDao {

	@Autowired
	private Company config;
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmMeeting> listMeetings(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try{
		SQLQuery query = session.createSQLQuery("CALL listCrmMeetings()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return new ArrayList<CrmMeeting>();
	}

	@Override
	public boolean insertMeeting(CrmMeeting meeting) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(meeting.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			DateTimeOperation toLocalDateTime = new DateTimeOperation();
			meeting.setMeetingId(IdAutoGenerator("AC_ME", meeting.getMeDataSource()));
			meeting.setMeetingStartDate(toLocalDateTime.convertStringToLocalDateTime(meeting.getStartDate()));
			meeting.setMeetingEndDate(toLocalDateTime.convertStringToLocalDateTime(meeting.getEndDate()));
			meeting.setMeetingCreateDate(LocalDateTime.now());
			session.save(meeting);
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
	public boolean updateMeeting(CrmMeeting meeting) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(meeting.getMeDataSource()));
		Session session = getSessionFactory().openSession();
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
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteMeeting(CrmMeeting meeting) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(meeting.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(meeting);
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
	public Object findMeetingById(String meetingId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findCrmMeetingById(:meetingId)");
			query.setParameter("meetingId", meetingId);
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
	public CrmMeeting findMeetingDetailsById(String meetingId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmMeeting.class);
			criteria.add(Restrictions.eq("meetingId", meetingId));
			return (CrmMeeting) criteria.uniqueResult();
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
	public List<CrmMeeting> listMeetingsRelatedToLead(String leadId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listMeetingsRelatedToLead(:leadId)");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmMeeting> listMeetingsRelatedToOpportunity(String opId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listMeetingsRelatedToOpportunity(:opId)");
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmMeeting> listMeetingsRelatedToModule(String moduleId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listMeetingsRelatedToModule(:moduleId)");
				query.setParameter("moduleId", moduleId);
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
	public Map<String, Object> listMeetingsForMobile(int rowNum, int pageNum, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			session.beginTransaction();
			String sqlCount = "Select count(m.M_ID) from crm_meeting m";
			SQLQuery countQuery = session.createSQLQuery(sqlCount);
			Long countResults = ((Number)countQuery.uniqueResult()).longValue();
			int totalPageNumber = (int) Math.ceil((double)countResults / rowNum);
			SQLQuery query = session.createSQLQuery(""
					+ "SELECT "
						+ "M.M_ID meetingId,"
						+ "M.M_Subject meetingSubject, "
						+ "DATE_FORMAT(M.M_StartDate,'%d/%m/%Y %h:%i %p') meetingStartDate,"
						+ "DATE_FORMAT(M.M_EndDate,'%d/%m/%Y %h:%i %p') meetingEndDate,"
						+ "M.M_Duration meetingDuration, "
						+ "M.M_StatusID statusId, "
						+ "(SELECT MS_Name FROM crm_meeting_status WHERE MS_ID = M.M_StatusID) statusName,"
						+ "M.M_RToType meetingRelatedToModuleType,"
						+ "M.M_RToID meetingRelatedToModuleId,"
						+ "M.M_Location meetingLocation,"
						+ "M.M_ATo userID,"
						+ "(SELECT UName FROM tbluser WHERE UID = M.M_ATo) username,"
						+ "M.M_CBy meetingCreateBy, "
						+ "DATE_FORMAT(M.M_CDate,'%d/%m/%Y %h:%i %p') meetingCreateDate, "
						+ "M.M_MBy meetingModifiedBy, "
						+ "CAST(M.M_MDate AS date) meetingModifiedDate "
					+ "FROM "
						+ "crm_meeting M "
					+ "WHERE M.M_ATo = '"+dataSource.getUserid()+"' OR M.M_CBy = '"+dataSource.getUserid()+"'");
			query.setFirstResult((pageNum -1) * rowNum);
			query.setMaxResults(rowNum);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			session.getTransaction().commit();
			if(query.list().isEmpty()){
				map.put("meetings", null);
			} else {
				map.put("meetings", query.list());
			}
			map.put("totalPage", totalPageNumber);
			map.put("status", HttpStatus.OK.value());
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return map;
	}
}
