package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmOpportunitySaleOrderDao;
import com.balancika.crm.model.CrmOpportunitySaleOrder;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmOpportunitySaleOrderDaoImpl implements CrmOpportunitySaleOrderDao{

	@Override
	public boolean insertOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		Session session = new HibernateSessionFactory().getSessionFactory(opSaleOrder.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(opSaleOrder);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		Session session = new HibernateSessionFactory().getSessionFactory(opSaleOrder.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(opSaleOrder);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		Session session = new HibernateSessionFactory().getSessionFactory(opSaleOrder.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(opSaleOrder);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public CrmOpportunitySaleOrder findOpportunitySaleOrder(int opSaleOrderId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			return (CrmOpportunitySaleOrder)session.get(CrmOpportunitySaleOrder.class, opSaleOrderId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public Integer checkOpportunitySaleOrderIsExist(String opId, String saleOrderId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmOpportunitySaleOrder.class);
			criteria.add(Restrictions.and(Restrictions.eq("opId", opId), Restrictions.eq("saleId", saleOrderId)))
					.setProjection(Projections.rowCount());
			return ((Number)criteria.uniqueResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		
	}

	@Override
	public Object viewOpportunitySaleOrder(int opSaleOrderId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL viewOpportunitySaleOrderById(:opSaleOrderId)");
			query.setParameter("opSaleOrderId", opSaleOrderId)
				 .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		
	}

	
}
