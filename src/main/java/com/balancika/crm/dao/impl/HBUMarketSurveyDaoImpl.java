package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.HBUMarketSurveyDao;
import com.balancika.crm.model.HBUCustomer;
import com.balancika.crm.model.HBUItem;
import com.balancika.crm.model.HBUMarketSurvey;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class HBUMarketSurveyDaoImpl extends CrmIdGenerator implements HBUMarketSurveyDao{

	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean InsertMarketSurvey(HBUMarketSurvey survey) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(survey.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			survey.setMsId(IdAutoGenerator("MS", survey.getMeDataSource()));
			survey.setMsDate(LocalDateTime.now());
			session.save(survey);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateMarketSurvey(HBUMarketSurvey survey) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(survey.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(survey);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteMakeySurvey(HBUMarketSurvey survey) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(survey.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(survey);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public HBUMarketSurvey findMarketSurveyById(String msId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(HBUMarketSurvey.class);
			criteria.add(Restrictions.eq("msId", msId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			session.getTransaction().commit();
			return (HBUMarketSurvey)criteria.uniqueResult();
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
	public Map<String, Object> createMarketSurveyStartup(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(HBUItem.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			session.getTransaction().commit();
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("ITEMS", criteria.list());
			map.put("CUSTOMERS", listCustomers(session));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<HBUCustomer> listCustomers(Session session){
		session.beginTransaction();
		Criteria criteria = session.createCriteria(HBUCustomer.class);
		session.getTransaction().commit();
		return (List<HBUCustomer>)criteria.list();
	}

	@Override
	public Map<String, Object> updateMaketSurveyStartup(String msId, MeDataSource dataSource) {
		return null;
	}

}
