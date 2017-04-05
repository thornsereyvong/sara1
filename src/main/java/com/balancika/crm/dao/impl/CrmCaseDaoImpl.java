package com.balancika.crm.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.balancika.crm.utilities.AppUtilities;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DBConnection;
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
			SQLQuery query = session.createSQLQuery("CALL crmDeleteModuleRelatedToCase(:caseId)");
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

	@Override
	public Map<String, Object> viewCaseById(String caseId, String userId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>(); 
		try(Connection con = DBConnection.getConnection(dataSource)){
			CallableStatement cs= con.prepareCall("{call crmViewCaseById(?,?)}");
			cs.setString(1, caseId);
			cs.setString(2, userId);
			boolean isResultSet = cs.execute();
			int rsCount = 0;
			String[] key = {
					"TASKS","TASK_STATUS","EVENTS","EVENT_LOCATION","CALLS","CALL_STATUS",
					"MEETINGS","MEETING_STATUS","NOTES","ASSIGN_TO","TAG_TO",
					"CONTACTS","CASE_ORIGIN","CASE_PRIORITY","CASE_STATUS","CASE_TYPE",
					"CUSTOMERS","ITEMS","ALL_USERS","ARTICLES"
					}; // 
			while(isResultSet){
				ResultSet rs = cs.getResultSet();
				map.put(key[rsCount], AppUtilities.aliasToMaps(rs));
				rs.close();
				isResultSet = cs.getMoreResults();
				rsCount++;
			}
			CallableStatement cst = con.prepareCall("{call findCrmCaseById(?)}");
			cst.setString(1, caseId);
			map.put("CASE", AppUtilities.aliasToSingleMap(cst.executeQuery()));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
