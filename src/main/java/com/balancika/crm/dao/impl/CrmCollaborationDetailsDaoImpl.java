package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCollaborationDetailsDao;
import com.balancika.crm.model.CrmCollaborationDetails;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmCollaborationDetailsDaoImpl implements CrmCollaborationDetailsDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertCollaborationDetails(CrmCollaborationDetails details) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			details.setCreateDate(LocalDateTime.now());
			session.save(details);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateCollaborationDetails(CrmCollaborationDetails details) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(details);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteCollaborationDetails(int detailsId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmCollaborationDetails details = new CrmCollaborationDetails();
			details.setCommentId(detailsId);
			session.delete(details);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCollaborationDetails> listCollaborationDetails() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(CrmCollaborationDetails.class);
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public CrmCollaborationDetails findCollaborationDetailsById(int detailsId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		CrmCollaborationDetails details = (CrmCollaborationDetails) session.get(CrmCollaborationDetails.class, detailsId);
		details.setFormatCreateDate(new DateTimeOperation().reverseLocalDateTimeToString(details.getCreateDate()));
		return details;
	}
}
