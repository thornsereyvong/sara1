package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCollaborationDetailsDao;
import com.balancika.crm.model.CrmCollaborationDetails;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmCollaborationDetailsDaoImpl implements CrmCollaborationDetailsDao{

	@Override
	public boolean insertCollaborationDetails(CrmCollaborationDetails details) {
		Session session = new HibernateSessionFactory().getSessionFactory(details.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			details.setCreateDate(LocalDateTime.now());
			session.save(details);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateCollaborationDetails(CrmCollaborationDetails details) {
		Session session = new HibernateSessionFactory().getSessionFactory(details.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(details);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteCollaborationDetails(CrmCollaborationDetails details) {
		Session session = new HibernateSessionFactory().getSessionFactory(details.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(details);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCollaborationDetails> listCollaborationDetails(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCollaborationDetails.class);
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public CrmCollaborationDetails findCollaborationDetailsById(int detailsId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			CrmCollaborationDetails details = (CrmCollaborationDetails) session.get(CrmCollaborationDetails.class, detailsId);
			details.setFormatCreateDate(new DateTimeOperation().reverseLocalDateTimeToString(details.getCreateDate()));
			return details;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}
}
