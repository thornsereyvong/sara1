package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCaseDao;
import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.CrmCaseSolution;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmCaseDaoImpl extends CrmIdGenerator implements CrmCaseDao{
	
	private SessionFactory sessionFactory;
		
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertCase(CrmCase cases) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(cases.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			cases.setCaseId(IdAutoGenerator("CS", cases.getMeDataSource()));
			cases.setFollowupDate(new DateTimeOperation().convertStringToLocalDateTime(cases.getConvertFollowupDate()));
			cases.setCreateDate(LocalDateTime.now());
			session.save(cases);
			session.getTransaction().commit();
			return true;	
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateCase(CrmCase cases) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(cases.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			if(cases.getConvertFollowupDate() != null)
				cases.setFollowupDate(new DateTimeOperation().convertStringToLocalDateTime(cases.getConvertFollowupDate()));
		
			session.update(cases);
			session.getTransaction().commit();
			return true;	
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteCase(CrmCase cases) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(cases.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_case WHERE CS_ID = :caseId");
			query.setParameter("caseId", cases.getCaseId());
			if(query.executeUpdate() > 0){
				session.getTransaction().commit();
				return true;	
			}
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCase> listCases(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmCases()");
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
	public Object findCaseById(String caseId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findCrmCaseById(:caseId)");
			query.setParameter("caseId", caseId);
			query.setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
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
	public CrmCase findCaseDetailsById(String caseId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCase.class);
			criteria.add(Restrictions.eq("caseId", caseId));
			return (CrmCase)criteria.uniqueResult();
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
	public boolean updateCustomFieldOfCase(CrmCase cases) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(cases.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			/*SQLQuery query = session.createSQLQuery("DELETE FROM crm_case_detail WHERE CS_ID = :caseId ;");
			query.setParameter("caseId", cases.getCaseId());*/
			session.saveOrUpdate(cases);
			session.getTransaction().commit();
			return  true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateCase(CrmCaseSolution cases) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(cases.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();	
			cases.setResolvedDate(new DateTimeOperation().convertStringToLocalDateTime(cases.getConvertResolvedDate()));
			session.update(cases);
			session.getTransaction().commit();
			return true;	
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		}catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}
	
}
