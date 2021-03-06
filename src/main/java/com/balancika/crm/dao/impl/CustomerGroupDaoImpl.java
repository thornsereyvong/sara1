package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CustomerGroupDao;
import com.balancika.crm.model.CustomerGroup;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CustomerGroupDaoImpl implements CustomerGroupDao{
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerGroup> listCustomerGroups(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CustomerGroup.class);
			criteria.addOrder(Order.asc("custGroupId"));
			return criteria.list();
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
