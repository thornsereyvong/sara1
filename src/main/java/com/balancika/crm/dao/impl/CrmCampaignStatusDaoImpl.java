package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCampaignStatusDao;
import com.balancika.crm.model.CrmCampaignStatus;

@Repository
public class CrmCampaignStatusDaoImpl implements CrmCampaignStatusDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean addCampaignStatus(CrmCampaignStatus status) {
		Session session = transactionManager.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			session.save(status);
			session.getTransaction().commit();
			return true;
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateCampaignStatus(CrmCampaignStatus status) {
		Session session = transactionManager.getSessionFactory().openSession();
		try{
			session.beginTransaction();
			session.update(status);
			session.getTransaction().commit();
			return true;
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public String deleteCampaignStatus(int statusID) {
		Session session = transactionManager.getSessionFactory().openSession();
		CrmCampaignStatus status = new CrmCampaignStatus();
		try{
			session.beginTransaction();
			status.setStatusID(statusID);
			session.delete(status);
			session.getTransaction().commit();
			session.close();
			return "OK";
		} catch (ConstraintViolationException e){
			session.getTransaction().rollback();
			e.getMessage();
			session.close();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.getMessage();
			session.close();
			return "FAILED";
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCampaignStatus> listAllCampaignStatus() {
		
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmCampaignStatus.class);
		criteria.addOrder(Order.asc("statusID"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//criteria.setProjection(Projections.groupProperty(""));
		return criteria.list();
	}

	@Override
	public CrmCampaignStatus findCampaignStatusById(int statusID) {
		return (CrmCampaignStatus)transactionManager.getSessionFactory().getCurrentSession().get(CrmCampaignStatus.class, statusID);
	}

}
