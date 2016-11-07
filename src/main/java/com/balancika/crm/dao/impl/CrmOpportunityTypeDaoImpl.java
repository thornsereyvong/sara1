package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmOpportunityTypeDao;
import com.balancika.crm.model.CrmOpportunityType;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmOpportunityTypeDaoImpl implements CrmOpportunityTypeDao {

	@Override
	public boolean insertOpportunityType(CrmOpportunityType type) {
		Session session = new HibernateSessionFactory().getSessionFactory(type.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(type);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateOpportunityType(CrmOpportunityType type) {
		Session session = new HibernateSessionFactory().getSessionFactory(type.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(type);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public String deleteOpportunityType(CrmOpportunityType type) {
		Session session = new HibernateSessionFactory().getSessionFactory(type.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(type);
			session.getTransaction().commit();
			return "OK";
		} catch(ConstraintViolationException ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
			return "FOREIGN_KEY_CONSTRAIN";
		}catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
		}
		return "FAILED";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunityType> listOpportunityTypes(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmOpportunityType.class);
			criteria.addOrder(Order.asc("otId"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public CrmOpportunityType findOpportunityTypeById(int typeID, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			return (CrmOpportunityType) session.get(CrmOpportunityType.class, typeID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
