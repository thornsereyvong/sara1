package com.balancika.crm.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmOpportunitySaleOrderDao;
import com.balancika.crm.model.CrmOpportunitySaleOrder;

@Repository
public class CrmOpportunitySaleOrderDaoImpl implements CrmOpportunitySaleOrderDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(opSaleOrder);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(opSaleOrder);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteOpportunitySaleOrder(int opSaleOrderId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmOpportunitySaleOrder opSaleOrder = new CrmOpportunitySaleOrder();
			opSaleOrder.setOpSaleId(opSaleOrderId);
			session.delete(opSaleOrder);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.close();
		}
		return false;
	}

	@Override
	public CrmOpportunitySaleOrder findOpportunitySaleOrder(int opSaleOrderId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
