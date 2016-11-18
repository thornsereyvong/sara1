package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmOpportunityContactDao;
import com.balancika.crm.model.CrmOpportunityContact;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmOpportunityContactDaoImpl implements CrmOpportunityContactDao{

	private SessionFactory sessionFactory;
	
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insterOpportunityContact(CrmOpportunityContact opCon) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(opCon.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(opCon);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateOpportunityContact(CrmOpportunityContact opCon) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(opCon.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(opCon);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteOpportunityContact(CrmOpportunityContact opCon) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(opCon.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(opCon);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public CrmOpportunityContact findOpportunityContactById(int opConId, MeDataSource dataSource) {
		setSessionFactory( new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			return (CrmOpportunityContact)session.get(CrmOpportunityContact.class, opConId);
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
		
	}

	@Override
	public Integer checkOpportunityContactIsExist(String opId, String conId, MeDataSource dataSource) {
		setSessionFactory( new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmOpportunityContact.class);
			criteria.add(Restrictions.and(Restrictions.eq("opId", opId),Restrictions.eq("conId", conId)));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("opId"), "opId")
					.add(Projections.property("conId"), "opId"));
			criteria.setProjection(Projections.rowCount());
			return ((Number)criteria.uniqueResult()).intValue();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@Override
	public Object viewOpportunityContactById(int opConId, MeDataSource dataSource) {
		setSessionFactory( new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL viewOpportunityById(:opConId)");
			query.setParameter("opConId", opConId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

}
