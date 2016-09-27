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

	@Override
	public Integer checkOpportunityQuotationIsExist(String opId, String quoteId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmOpportunityQuotation.class);
		criteria.add(Restrictions.and(Restrictions.eq("opId", opId), Restrictions.eq("quoteId", quoteId)))
				.setProjection(Projections.rowCount());
		return ((Number)criteria.uniqueResult()).intValue();
	}

	@Override
	public Object viewOpportunityQuotationById(int opQuoteId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL viewOpportunityQuotationById(:opQuoteId)");
		query.setParameter("opQuoteId", opQuoteId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

}
