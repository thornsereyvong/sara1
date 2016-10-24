package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateConfiguration;
import com.balancika.crm.dao.CrmDatabaseConfigurationDao;
import com.balancika.crm.model.CrmDatabaseConfiguration;

@Repository
public class CrmDatabaseConfigurationDaoImpl implements CrmDatabaseConfigurationDao{
	
	@Autowired
	private CrmDatabaseConfiguration config;
	
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public void changeDataSource(String dbName) {
		new CrmDatabaseConfiguration().setDbName(dbName);
	}

	@Override
	public Object listDatabases() {
		Session session = sessionFactory.openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL spListDatabaseRelatedToCRM()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			Object databases =  query.list();
			session.close();
			return databases;
		} catch (Exception e) {
			e.printStackTrace();
			session.close();
		}
		return null;
	}
}
