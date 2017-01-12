package com.balancika.crm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
			/*if(survey.getMsId() == null){
				survey.setMsId(IdAutoGenerator("MS", survey.getMeDataSource()));
			}*/
			/*SQLQuery query  = session.createSQLQuery("DELETE FROM hbu_market_survey_details WHERE MarketSurveyID = :msId ;");
			query.setParameter("msId", survey.getMsId());
			query.executeUpdate();*/
			survey.setMsId(IdAutoGenerator("MS", survey.getMeDataSource()));
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
			Map<String, Object> map = new HashMap<String, Object>();
			List<HBUItem> items = listItems(dataSource);
			List<HBUCustomer> customers = listCustomers(dataSource);
			map.put("ITEMS", items);
			map.put("CUSTOMERS", customers);
			return map;
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	private List<HBUCustomer> listCustomers(MeDataSource dataSource){
		//System.out.println(dataSource.toString());
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(HBUCustomer.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			session.getTransaction().commit();
			List<HBUCustomer> customers =  (List<HBUCustomer>)criteria.list();
			return customers;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
		
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	private List<HBUItem> listItems(MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(HBUItem.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			session.getTransaction().commit();
			List<HBUItem> items = (List<HBUItem>)criteria.list();
			return items;
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
	public Map<String, Object> updateMaketSurveyStartup(String msId, MeDataSource dataSource) {
		return null;
	}

	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	@Override
	public List<HBUMarketSurvey> listMarketSurveys(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(HBUMarketSurvey.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			session.getTransaction().commit();
			List<HBUMarketSurvey> surveys = (List<HBUMarketSurvey>)criteria.list();
			return surveys;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@Override
	public HBUMarketSurvey findMarketSurveyByItemID(String itemId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(HBUMarketSurvey.class);
			criteria.add(Restrictions.eq("item.itemId", itemId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			session.getTransaction().commit();
			HBUMarketSurvey survey = (HBUMarketSurvey)criteria.uniqueResult();
			return survey;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

}
