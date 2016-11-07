package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmMeetingStatusDao;
import com.balancika.crm.model.CrmMeetingStatus;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmMeetingStatusDaoImpl implements CrmMeetingStatusDao{

	@Override
	public boolean insertMeetingStatus(CrmMeetingStatus status) {
		Session session = new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(status);
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
	public boolean updateMeetingStatus(CrmMeetingStatus status) {
		Session session = new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(status);
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
	public String deleteMeetingStatus(CrmMeetingStatus status) {
		Session session = new HibernateSessionFactory().getSessionFactory(status.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
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
	public CrmMeetingStatus findMeetingStatusById(int statusId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmMeetingStatus.class);
			criteria.add(Restrictions.eq("statusId", statusId));
			return (CrmMeetingStatus)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmMeetingStatus> listMeetingStatus(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmMeetingStatus.class);
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
}
