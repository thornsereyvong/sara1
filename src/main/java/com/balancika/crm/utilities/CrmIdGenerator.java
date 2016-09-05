package com.balancika.crm.utilities;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class CrmIdGenerator {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public String IdAutoGenerator(String moduleId){
		
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT generateAutoId(:moduleId)");
		query.setParameter("moduleId", moduleId);
		
		return (String)query.uniqueResult();
	}
	
	
	public String ameIdAutoGenerator(String moduleId){
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createSQLQuery("SELECT fnGetLastID(:moduleId)");
		query.setParameter("moduleId", moduleId);
		
		return (String)query.uniqueResult();
	}
}
