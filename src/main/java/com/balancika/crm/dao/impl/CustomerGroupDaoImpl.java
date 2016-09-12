package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CustomerGroupDao;
import com.balancika.crm.model.CustomerGroup;

@Repository
public class CustomerGroupDaoImpl implements CustomerGroupDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<CustomerGroup> listCustomerGroups() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CustomerGroup.class);
		criteria.addOrder(Order.asc("custGroupId"));
		return criteria.list();
	}

}
