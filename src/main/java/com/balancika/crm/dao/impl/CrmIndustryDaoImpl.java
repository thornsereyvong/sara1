package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmIndustryDao;
import com.balancika.crm.model.CrmIndustry;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmIndustryDaoImpl implements CrmIndustryDao {

	@Override
	public boolean insertIndustry(CrmIndustry industry) {
		Session session = new HibernateSessionFactory().getSessionFactory(industry.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(industry);
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
	public boolean updateIndustry(CrmIndustry industry) {
		Session session = new HibernateSessionFactory().getSessionFactory(industry.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(industry);
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
	public boolean deleteIndustry(CrmIndustry industry) {
		Session session = new HibernateSessionFactory().getSessionFactory(industry.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(industry);
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmIndustry> listIndustries(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmIndustry.class);
			criteria.addOrder(Order.asc("industID"));
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
	public CrmIndustry finIndustryById(int industID, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			return (CrmIndustry) session.get(CrmIndustry.class, industID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
