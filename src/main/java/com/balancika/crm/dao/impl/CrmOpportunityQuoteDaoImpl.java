package com.balancika.crm.dao.impl;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmOpportunityQuoteDao;
import com.balancika.crm.model.CrmOpportunityQuotation;

@Repository
public class CrmOpportunityQuoteDaoImpl implements CrmOpportunityQuoteDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertOpportunityQuote(CrmOpportunityQuotation opQuote) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(opQuote);
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
	public boolean updateOpportunityQuote(CrmOpportunityQuotation opQuote) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(opQuote);
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
	public boolean deleteOpportunityQuote(int opQuoteId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmOpportunityQuotation opQuote = new CrmOpportunityQuotation();
			opQuote.setOpQuoteId(opQuoteId);
			session.delete(opQuote);
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
	public CrmOpportunityQuotation findOpportunityQuotationById(int opQuoteId) {
		return (CrmOpportunityQuotation)transactionManager.getSessionFactory().getCurrentSession().get(CrmOpportunityQuotation.class, opQuoteId);
	}

}
