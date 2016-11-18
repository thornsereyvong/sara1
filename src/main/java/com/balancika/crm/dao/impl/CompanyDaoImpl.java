package com.balancika.crm.dao.impl;

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
}
