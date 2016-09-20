package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCaseDao;
import com.balancika.crm.model.CrmCase;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmCaseDaoImpl extends CrmIdGenerator implements CrmCaseDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertCase(CrmCase cases) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			cases.setCaseId(IdAutoGenerator("CS"));
			cases.setCreateDate(LocalDateTime.now());
			session.save(cases);
			session.getTransaction().commit();
			return true;	
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateCase(CrmCase cases) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(cases);
			session.getTransaction().commit();
			return true;	
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteCase(String caseId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_case WHERE CS_ID = :caseId");
			query.setParameter("caseId", caseId);
			if(query.executeUpdate() > 0){
				session.getTransaction().commit();
				return true;	
			}
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCase> listCases() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listCrmCases()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public Object findCaseById(String caseId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL findCrmCaseById(:caseId)");
		query.setParameter("caseId", caseId);
		query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

	@Override
	public CrmCase findCaseDetailsById(String caseId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmCase.class);
		criteria.add(Restrictions.eq("caseId", caseId));
		return (CrmCase)criteria.uniqueResult();
	}

}
