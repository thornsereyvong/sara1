package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmDatabaseConfigurationDao;
import com.balancika.crm.model.CrmDatabaseConfiguration;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmDatabaseConfigurationDaoImpl extends CrmIdGenerator implements CrmDatabaseConfigurationDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertDatabaseConfiguration(CrmDatabaseConfiguration databaseConfiguration) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			databaseConfiguration.setDbID(IdAutoGenerator("DB"));
			session.save(databaseConfiguration);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateDatabaseConfiguration(CrmDatabaseConfiguration databaseConfiguration) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(databaseConfiguration);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteDatabaseConfiguration(String dbId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmDatabaseConfiguration dbcondifg = new CrmDatabaseConfiguration();
			dbcondifg.setDbID(dbId);
			session.delete(dbcondifg);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public CrmDatabaseConfiguration findDatabaseConfigurationById(String dbId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmDatabaseConfiguration.class);
		criteria.add(Restrictions.eq("dbId", dbId));
		return (CrmDatabaseConfiguration)criteria.uniqueResult();
	}

	@Override
	public CrmDatabaseConfiguration createDatabase(CrmDatabaseConfiguration databaseConfiguration) {
		
		return null;
	}

}
