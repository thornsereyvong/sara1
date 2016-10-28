package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CompanyDao;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CompanyDaoImpl implements CompanyDao{

	@Override
	public Object listDatabases(MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
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