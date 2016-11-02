package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCallDao;
import com.balancika.crm.model.CrmCall;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.DateTimeOperation;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmCallDaoImpl extends CrmIdGenerator implements CrmCallDao {

	@Override
	public boolean insertCall(CrmCall call) {
		Session session = HibernateSessionFactory.getSessionFactory(call.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			DateTimeOperation toLocalDateTime = new DateTimeOperation();
			call.setCallId(IdAutoGenerator("AC_CL"));
			call.setCallStartDate(toLocalDateTime.convertStringToLocalDateTime(call.getStartDate()));
			call.setCallCreateDate(LocalDateTime.now());
			session.save(call);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateCall(CrmCall call) {
		Session session = HibernateSessionFactory.getSessionFactory(call.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			DateTimeOperation toLocalDateTime = new DateTimeOperation();
			call.setCallStartDate(toLocalDateTime.convertStringToLocalDateTime(call.getStartDate()));
			session.update(call);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteCall(CrmCall call) {
		Session session = HibernateSessionFactory.getSessionFactory(call.getMeDataSource()).openSession();
		try {
			SQLQuery query 	= session.createSQLQuery("DELETE FROM crm_call WHERE CL_ID = :callId");
			query.setParameter("callId", call.getCallId());
			if (query.executeUpdate() > 0) {
				return true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCall> listCalls(MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmCalls()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public Object findCallById(CrmCall call) {
		Session session = HibernateSessionFactory.getSessionFactory(call.getMeDataSource()).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findCrmCallById(:callId)");
			query.setParameter("callId", call.getCallId());
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public CrmCall listCallStructureDetailsById(CrmCall call) {
		Session session = HibernateSessionFactory.getSessionFactory(call.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCall.class);
			criteria.add(Restrictions.eq("callId", call.getCallId()));
			session.getTransaction().commit();
			return (CrmCall) criteria.uniqueResult();
		} catch (HibernateException e) {
			session.getTransaction();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCall> listCallsRelatedToLead(String leadId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCallsRelatedToLead(:leadId)");
			query.setParameter("leadId", leadId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCall> listCallsRelatedToOpportunity(String opId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCallsRelatedToOpportunity(:opId)");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCall> listCallsRelatedToModule(String moduleId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCallsRelatedToModule(:moduleId)");
			query.setParameter("moduleId", moduleId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
