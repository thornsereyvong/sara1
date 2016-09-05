package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmOpportunityStageDao;
import com.balancika.crm.model.CrmOpportunityStage;

@Repository
public class CrmOpportunityStageDaoImpl implements CrmOpportunityStageDao{
	
	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertOpportunityStage(CrmOpportunityStage opStage) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(opStage);
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
	public boolean updateOpportunityStage(CrmOpportunityStage opStage) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(opStage);
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
	public String deleteOpportunityStage(int opStageId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmOpportunityStage stage = new CrmOpportunityStage();
			stage.setOsId(opStageId);
			session.delete(stage);
			session.getTransaction().commit();
			return "OK";
		} catch(ConstraintViolationException ex){
			ex.getMessage();
			session.getTransaction().rollback();
			return "FOREIGN_KEY_CONSTRAIN";
		}catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return "FAILED";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmOpportunityStage> listOpportunityStages() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmOpportunityStage.class);
		criteria.addOrder(Order.asc("osId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmOpportunityStage findOpportunityStage(int opStageId) {
		return (CrmOpportunityStage)transactionManager.getSessionFactory().getCurrentSession().get(CrmOpportunityStage.class, opStageId);
	}

}
