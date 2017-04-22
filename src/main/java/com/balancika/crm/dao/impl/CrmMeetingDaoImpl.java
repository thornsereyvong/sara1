package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmMeetingDao;
import com.balancika.crm.model.CrmMeeting;
import com.balancika.crm.model.CrmMeetingCheckin;
import com.balancika.crm.model.CrmMeetingStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.DateTimeOperation;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmMeetingDaoImpl extends CrmIdGenerator implements CrmMeetingDao {

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
			SQLQuery query = session.createSQLQuery("CALL crmDeleteMeeting(:meetId)");
			query.setParameter("meetId", meeting.getMeetingId());
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

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Map<String, Object> listMeetingsForMobile(int rowNum, int pageNum, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		try{
			session.beginTransaction();
			String sqlCount = "Select count(m.M_ID) from crm_meeting m WHERE (M.M_ATo = '"+dataSource.getUserid()+"' OR M.M_CBy = '"+dataSource.getUserid()+"') AND (M.M_StatusID is NULL OR M.M_StatusID = (SELECT MS_ID FROM crm_meeting_status WHERE MS_Name ='Planned'))";
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
						+ "COALESCE((SELECT MS_Name FROM crm_meeting_status WHERE MS_ID = M.M_StatusID),'') statusName,"
						+ "M.M_RToType meetingRelatedToModuleType,"
						+ "M.M_RToID meetingRelatedToModuleId,"
						+ "M.M_Location meetingLocation,"
						+ "DATE_FORMAT(M.M_CDate,'%d/%m/%Y %h:%i %p') meetingCreateDate, "
						+ "CAST(M.M_MDate AS date) meetingModifiedDate "
					+ " FROM "
						+ "crm_meeting M "
					+ "WHERE (M.M_ATo = '"+dataSource.getUserid()+"' OR M.M_CBy = '"+dataSource.getUserid()+"') AND (M.M_StatusID is NULL OR M.M_StatusID = (SELECT MS_ID FROM crm_meeting_status WHERE MS_Name ='Planned'))");
			query.setFirstResult((pageNum -1) * rowNum);
			query.setMaxResults(rowNum);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<Object> meetings = query.list();
			if(meetings.isEmpty()){
				map.put("meetings", null);
				map.put("status", HttpStatus.NOT_FOUND.value());
			} else {
				map.put("meetings", meetings);
				map.put("status", HttpStatus.OK.value());
			}
			map.put("totalPage", totalPageNumber);
			session.getTransaction().commit();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return map;
	}

	@Override
	public Map<String, Object> meetingCheckIn(CrmMeetingCheckin checkin) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(checkin.getDataSource()));
		Session session = getSessionFactory().openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			session.beginTransaction();
			
			//Clear all meeting image and meeting audio that related to this meeting id
			SQLQuery query = session.createSQLQuery("CALL crmDeleteMeetingImageAndAudio(:meetId)");
			query.setParameter("meetId", checkin.getMeetId());
			query.executeUpdate();
			//end
			
			//get meeting status id by meeting status name to insert to CrmMeetingCheckin object
			Criteria criteria = session.createCriteria(CrmMeetingStatus.class);
			criteria.add(Restrictions.eq("statusName", "Held"));
			CrmMeetingStatus status = (CrmMeetingStatus)criteria.uniqueResult();
			checkin.setStatusId(status.getStatusId());
			//end
			
			session.update(checkin);
			map.put("msg", "success");
			map.put("status", HttpStatus.OK.value());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			map.put("msg", "failed");
			map.put("status", HttpStatus.INTERNAL_SERVER_ERROR.value());
		} finally {
			session.close();
			sessionFactory.close();
		}
		return map;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> searchMeeting(int rowNum, int pageNum, String str, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			session.beginTransaction();
			String sqlCount = "Select count(m.M_ID) from crm_meeting m WHERE (M.M_ATo = '"+dataSource.getUserid()+"' OR M.M_CBy = '"+dataSource.getUserid()+"') AND (M.M_StatusID is NULL OR M.M_StatusID = (SELECT MS_ID FROM crm_meeting_status WHERE MS_Name ='Planned')) AND (M.M_ID LIKE '"+str+"%' OR M.M_Subject LIKE '"+str+"%')";
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
						+ "COALESCE((SELECT MS_Name FROM crm_meeting_status WHERE MS_ID = M.M_StatusID),'') statusName,"
						+ "M.M_RToType meetingRelatedToModuleType,"
						+ "M.M_RToID meetingRelatedToModuleId,"
						+ "M.M_Location meetingLocation,"
						+ "DATE_FORMAT(M.M_CDate,'%d/%m/%Y %h:%i %p') meetingCreateDate, "
						+ "CAST(M.M_MDate AS date) meetingModifiedDate "
					+ " FROM "
						+ "crm_meeting M "
					+ "WHERE (M.M_ATo = '"+dataSource.getUserid()+"' OR M.M_CBy = '"+dataSource.getUserid()+"') AND (M.M_StatusID is NULL OR M.M_StatusID = (SELECT MS_ID FROM crm_meeting_status WHERE MS_Name ='Planned')) AND (M.M_ID LIKE '"+str+"%' OR M.M_Subject LIKE '"+str+"%')");
			query.setFirstResult((pageNum -1) * rowNum);
			query.setMaxResults(rowNum);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<Object> meetings = query.list();
			if(meetings.isEmpty()){
				map.put("meetings", null);
				map.put("status", HttpStatus.NOT_FOUND.value());
			} else {
				map.put("meetings", meetings);
				map.put("status", HttpStatus.OK.value());
			}
			map.put("totalPage", totalPageNumber);
			session.getTransaction().commit();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}

	@Override
	public Map<String, Object> findImagesAndAudioRelatedToMeeting(String meetId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			session.beginTransaction();
			SQLQuery image = session.createSQLQuery("SELECT IMG_ID imgId, IMG_Path imgPath FROM crm_meeting_image WHERE M_ID = :meetId");
			image.setParameter("meetId", meetId);
			image.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			map.put("images", image.list());
			
			SQLQuery audio = session.createSQLQuery("SELECT AU_ID audioId, AU_Path audioPath FROM crm_meeting_audio WHERE M_ID = :meetId");
			audio.setParameter("meetId", meetId);
			audio.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			map.put("audio", audio.list());
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
		return map;
	}
}
