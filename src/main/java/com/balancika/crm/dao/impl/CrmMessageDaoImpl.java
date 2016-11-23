package com.balancika.crm.dao.impl;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmMessageDao;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmMessageDaoImpl implements CrmMessageDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public String getMessage(String code, String module, String moduleId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("SELECT crmGenerateMessage(:code, :module :moduleId)");
			query.setParameter("code", code);
			query.setParameter("module", module);
			query.setParameter("moduleId", moduleId);
			return (String)query.uniqueResult();
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
	public String getMessage(String code, String module, int moduleId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("SELECT crmGenerateMessage(:code, :module :moduleId)");
			query.setParameter("code", code);
			query.setParameter("module", module);
			query.setParameter("moduleId", ""+moduleId);
			return (String)query.uniqueResult();
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
