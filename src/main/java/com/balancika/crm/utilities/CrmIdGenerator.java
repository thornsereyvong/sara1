package com.balancika.crm.utilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.model.MeDataSource;

public class CrmIdGenerator {
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public String IdAutoGenerator(String moduleId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createSQLQuery("SELECT generateAutoId(:moduleId)");
			query.setParameter("moduleId", moduleId);
			
			return (String)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
	
	
	public String ameIdAutoGenerator(String moduleId, MeDataSource dataSource){
		
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Query query = session.createSQLQuery("SELECT fnGetLastID(:moduleId)");
			query.setParameter("moduleId", moduleId);
			
			return (String)query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}
}
