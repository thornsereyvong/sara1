package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmOpportunityContactDao;
import com.balancika.crm.model.CrmOpportunityContact;

@Repository
public class CrmOpportunityContactDaoImpl implements CrmOpportunityContactDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insterOpportunityContact(CrmOpportunityContact opCon) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(opCon);
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
	public boolean updateOpportunityContact(CrmOpportunityContact opCon) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(opCon);
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
	public boolean deleteOpportunityContact(int opConId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmOpportunityContact opCon = new CrmOpportunityContact();
			opCon.setOpConId(opConId);
			session.delete(opCon);
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
	public CrmOpportunityContact findOpportunityContactById(int opConId) {
		return (CrmOpportunityContact)transactionManager.getSessionFactory().getCurrentSession().get(CrmOpportunityContact.class, opConId);
	}

	@Override
	public Integer checkOpportunityContactIsExist(String opId, String conId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmOpportunityContact.class);
		criteria.add(Restrictions.and(Restrictions.eq("opId", opId),Restrictions.eq("conId", conId)));
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("opId"), "opId")
				.add(Projections.property("conId"), "opId"));
		criteria.setProjection(Projections.rowCount());
		return ((Number)criteria.uniqueResult()).intValue();
	}

}
