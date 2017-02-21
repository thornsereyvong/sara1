package com.balancika.crm.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmOpportunityLeadProjectDao;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmOpportunityLeadProjectDaoImpl implements CrmOpportunityLeadProjectDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addOpportunityLeadProject(String opId, int lpId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String sql = "INSERT INTO crm_opportunity_lead_project VALUES(:opId, :lpId);";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("opId", opId);
			query.setParameter("lpId", lpId);
			if (query.executeUpdate() > 0) {
				session.getTransaction().commit();
				return true;
			}
			
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
	public boolean deleteOpportunityLeadProject(String opId, int lpId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_opportunity_lead_project WHERE OPID = :opId AND LPID = :lpId ;");
			query.setParameter("opId", opId);
			query.setParameter("lpId", lpId);
			if (query.executeUpdate() > 0) {
				session.getTransaction().commit();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listOpportunityLeadProjectByOpId(String opId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(""
					+ "SELECT "
						+ "LP_ID id,"
						+ "LP_Name name,"
						+ "LP_AccountManager accountManager,"
						+ "LP_CompanyName company,"
						+ "LP_Email email,"
						+ "LP_StartDate startDate,"
						+ "LP_EndDate endDate "
					+ "FROM "
						+ "crm_lead_project "
					+ "WHERE "
						+ "LP_ID IN (SELECT LPID FROM crm_opportunity_lead_project WHERE OPID = :opId); ");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return new ArrayList<Object>();
	}

	@Override
	public Map<String, Object> startupOpportunityLeadProject(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery(""
					+ "SELECT "
						+ "LP_ID id,"
						+ "LP_Name name "
					+ "FROM "
						+ "crm_lead_project "
					+ "LEFT OUTER JOIN crm_opportunity_lead_project ON LP_ID = LPID "
					+ "WHERE "
						+ "OPID is NULL; ");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("PROJECT_STARTUP", query.list());
			//map.put("OP_PROJECT", listOpportunityLeadProjectByOpId(opId, dataSource));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}
}
