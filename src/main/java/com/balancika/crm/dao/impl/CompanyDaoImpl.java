package com.balancika.crm.dao.impl;

import java.util.HashMap;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CompanyDao;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CompanyDaoImpl implements CompanyDao{
	
	private SessionFactory sessionFactory;
	
	@Override
	public Object listDatabases(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("CALL spListDatabaseRelatedToCRM()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			Object databases =  query.list();
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return databases;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public Map<String, Object> listDatabaseForMobile(int pageSize, int pageNumber, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			session.beginTransaction();
			String sqlCount = "Select count(c.ComID) from tblcompany c";
			SQLQuery countQuery = session.createSQLQuery(sqlCount);
			Long countResults = ((Number)countQuery.uniqueResult()).longValue();
			int totalPageNumber = (int) ((countResults / pageSize));
			SQLQuery query = session.createSQLQuery("Select c.ComID comId, c.ComName comName, c.DBName dbName From tblcompany c");
			query.setFirstResult((pageNumber - 1) * pageSize);
			query.setMaxResults(pageSize);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			map.put("databases", query.list());
			map.put("totalPages", totalPageNumber);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return map;
	}
}
