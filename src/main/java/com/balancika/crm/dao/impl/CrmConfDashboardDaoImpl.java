package com.balancika.crm.dao.impl;

import java.util.List;

import javax.validation.ConstraintViolationException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmConfDashboardDao;
import com.balancika.crm.model.CrmConfDashboard;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmConfDashboardDaoImpl extends CrmIdGenerator implements CrmConfDashboardDao {
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertConf(CrmConfDashboard conf) {
		
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(conf.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(conf);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateConf(CrmConfDashboard conf) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteConf(CrmConfDashboard conf) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CrmConfDashboard> listConf(MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object findConfById(String conId, MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}
}
