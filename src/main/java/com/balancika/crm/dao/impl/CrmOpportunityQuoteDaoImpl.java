package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmOpportunityQuoteDao;
import com.balancika.crm.model.CrmOpportunityQuotation;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmOpportunityQuoteDaoImpl implements CrmOpportunityQuoteDao{

	@Override
	public boolean insertOpportunityQuote(CrmOpportunityQuotation opQuote) {
		Session session = new HibernateSessionFactory().getSessionFactory(opQuote.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(opQuote);
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
	public boolean updateOpportunityQuote(CrmOpportunityQuotation opQuote) {
		Session session = new HibernateSessionFactory().getSessionFactory(opQuote.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(opQuote);
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
	public boolean deleteOpportunityQuote(CrmOpportunityQuotation opQuote) {
		Session session = new HibernateSessionFactory().getSessionFactory(opQuote.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(opQuote);
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
	public CrmOpportunityQuotation findOpportunityQuotationById(int opQuoteId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			return (CrmOpportunityQuotation)session.get(CrmOpportunityQuotation.class, opQuoteId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		
	}

	@Override
	public Integer checkOpportunityQuotationIsExist(String opId, String quoteId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmOpportunityQuotation.class);
			criteria.add(Restrictions.and(Restrictions.eq("opId", opId), Restrictions.eq("quoteId", quoteId)))
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
	public Object viewOpportunityQuotationById(int opQuoteId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL viewOpportunityQuotationById(:opQuoteId)");
			query.setParameter("opQuoteId", opQuoteId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
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
