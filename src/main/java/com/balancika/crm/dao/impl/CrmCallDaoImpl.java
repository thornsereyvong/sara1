package com.balancika.crm.dao.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCallDao;
import com.balancika.crm.model.CrmCall;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmCallDaoImpl extends CrmIdGenerator implements CrmCallDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertCall(CrmCall call) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			call.setCallId(IdAutoGenerator("AC_CL"));
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
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
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
	public boolean deleteCall(String callId) {
		SQLQuery query = transactionManager.getSessionFactory().getCurrentSession()
											.createSQLQuery("DELETE FROM crm_call WHERE CL_ID = :callId");
		query.setParameter("callId", callId);
		if (query.executeUpdate() > 0) {
			return true;
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCall> listCalls() {
		SQLQuery query = transactionManager.getSessionFactory().getCurrentSession()
				.createSQLQuery("CALL listCrmCalls()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public Object findCallById(String callId) {
		SQLQuery query = transactionManager.getSessionFactory().getCurrentSession()
				.createSQLQuery("CALL findCrmCallById(:callId)");
		query.setParameter("callId", callId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

	@Override
	public CrmCall listCallStructureDetailsById(String callId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCall.class);
			criteria.add(Restrictions.eq("callId", callId));
			session.getTransaction().commit();
			return (CrmCall) criteria.uniqueResult();
		} catch (HibernateException e) {
			session.getTransaction();
		} finally {
			session.close();
		}
		return null;
	}

}