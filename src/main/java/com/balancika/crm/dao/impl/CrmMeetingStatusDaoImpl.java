package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmMeetingStatusDao;
import com.balancika.crm.model.CrmMeetingStatus;

@Repository
public class CrmMeetingStatusDaoImpl implements CrmMeetingStatusDao{

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertMeetingStatus(CrmMeetingStatus status) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(status);
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
	public boolean updateMeetingStatus(CrmMeetingStatus status) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(status);
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
	public String deleteMeetingStatus(int statusId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmMeetingStatus status = new CrmMeetingStatus();
			status.setStatusId(statusId);
			session.delete(status);
			session.getTransaction().commit();
			return "OK";
		} catch(ConstraintViolationException ex){
			ex.printStackTrace();
			session.getTransaction().rollback();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return "FAILED";
	}

	@Override
	public CrmMeetingStatus findMeetingStatusById(int statusId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmMeetingStatus.class);
		criteria.add(Restrictions.eq("statusId", statusId));
		return (CrmMeetingStatus)criteria.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmMeetingStatus> listMeetingStatus() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmMeetingStatus.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
}
