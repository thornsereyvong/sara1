package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmIndustryDao;
import com.balancika.crm.model.CrmIndustry;

@Repository
public class CrmIndustryDaoImpl implements CrmIndustryDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertIndustry(CrmIndustry industry) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(industry);
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
	public boolean updateIndustry(CrmIndustry industry) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(industry);
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
	public boolean deleteIndustry(int industID) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmIndustry industry = new CrmIndustry();
			industry.setIndustID(industID);
			session.delete(industry);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmIndustry> listIndustries() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmIndustry.class);
		criteria.addOrder(Order.asc("industID"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		// criteria.setProjection(Projections.groupProperty(""));
		return criteria.list();
	}

	@Override
	public CrmIndustry finIndustryById(int industID) {

		return (CrmIndustry) transactionManager.getSessionFactory().getCurrentSession().get(CrmIndustry.class, industID);
	}

}
