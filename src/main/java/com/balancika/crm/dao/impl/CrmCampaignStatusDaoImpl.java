package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCampaignStatusDao;
import com.balancika.crm.model.CrmCampaignStatus;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmCampaignStatusDaoImpl implements CrmCampaignStatusDao {

	@Override
	public boolean addCampaignStatus(CrmCampaignStatus status) {
		Session session = HibernateSessionFactory.getSessionFactory(status.getMeDataSource()).openSession();
		try{
			session.beginTransaction();
			session.save(status);
			session.getTransaction().commit();
			return true;
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateCampaignStatus(CrmCampaignStatus status) {
		Session session = HibernateSessionFactory.getSessionFactory(status.getMeDataSource()).openSession();
		try{
			session.beginTransaction();
			session.update(status);
			session.getTransaction().commit();
			return true;
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public String deleteCampaignStatus(CrmCampaignStatus status) {
		Session session = HibernateSessionFactory.getSessionFactory(status.getMeDataSource()).openSession();
		try{
			session.beginTransaction();
			session.delete(status);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return "OK";
		} catch (ConstraintViolationException e){
			session.getTransaction().rollback();
			e.printStackTrace();
			session.clear();
			session.close();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
			session.clear();
			session.close();
			return "FAILED";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCampaignStatus> listAllCampaignStatus(MeDataSource dataSource) {
		
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCampaignStatus.class);
			criteria.addOrder(Order.asc("statusID"));
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
	public CrmCampaignStatus findCampaignStatusById(int statusID, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			return (CrmCampaignStatus)session.get(CrmCampaignStatus.class, statusID);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

}
