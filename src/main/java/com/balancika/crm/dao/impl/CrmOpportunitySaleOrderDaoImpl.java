package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
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
		return (CrmOpportunitySaleOrder)transactionManager.getSessionFactory().getCurrentSession().get(CrmOpportunitySaleOrder.class, opSaleOrderId);
	}

	@Override
	public Integer checkOpportunitySaleOrderIsExist(String opId, String saleOrderId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmOpportunitySaleOrder.class);
		criteria.add(Restrictions.and(Restrictions.eq("opId", opId), Restrictions.eq("saleId", saleOrderId)))
				.setProjection(Projections.rowCount());
		return ((Number)criteria.uniqueResult()).intValue();
	}

	@Override
	public Object viewOpportunitySaleOrder(int opSaleOrderId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL viewOpportunitySaleOrderById(:opSaleOrderId)");
		query.setParameter("opSaleOrderId", opSaleOrderId)
			 .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

	
}
