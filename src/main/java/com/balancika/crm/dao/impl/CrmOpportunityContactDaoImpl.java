package com.balancika.crm.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
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

}
