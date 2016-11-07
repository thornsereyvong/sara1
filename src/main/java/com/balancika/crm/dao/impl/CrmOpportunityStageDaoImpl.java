package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmOpportunityStageDao;
import com.balancika.crm.model.CrmOpportunityStage;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmOpportunityStageDaoImpl implements CrmOpportunityStageDao{

	@Override
	public boolean insertOpportunityStage(CrmOpportunityStage opStage) {
		Session session = new HibernateSessionFactory().getSessionFactory(opStage.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(opStage);
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
	public boolean updateOpportunityStage(CrmOpportunityStage opStage) {
		Session session = new HibernateSessionFactory().getSessionFactory(opStage.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(opStage);
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
	public String deleteOpportunityStage(CrmOpportunityStage opStage) {
		Session session = new HibernateSessionFactory().getSessionFactory(opStage.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(opStage);
			session.getTransaction().commit();
			return "OK";
		} catch(ConstraintViolationException ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
			return "FOREIGN_KEY_CONSTRAIN";
		}catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return "FAILED";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunityStage> listOpportunityStages(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmOpportunityStage.class);
			criteria.addOrder(Order.asc("osId"));
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
	public CrmOpportunityStage findOpportunityStage(int opStageId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			return (CrmOpportunityStage)session.get(CrmOpportunityStage.class, opStageId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
}
