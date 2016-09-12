package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCollaborationDao;
import com.balancika.crm.model.CrmCollaboration;
import com.balancika.crm.services.CrmCollaborationTagsService;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmCollaborationDaoImpl implements CrmCollaborationDao{
	
	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Autowired
	private CrmCollaborationTagsService tagsService;

	@Override
	public boolean insertCollaboration(CrmCollaboration collaboration) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			collaboration.setColCreateDate(LocalDateTime.now());
			session.save(collaboration);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCollaboration(CrmCollaboration collaboration) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(collaboration);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCollaboration(int colId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmCollaboration collaboration = new CrmCollaboration();
			collaboration.setColId(colId);
			session.delete(collaboration);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCollaboration> listCollaborations() {
		Session session = transactionManager.getSessionFactory().openSession();
		List<CrmCollaboration> collaborations = new ArrayList<CrmCollaboration>();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCollaboration.class);
			criteria.addOrder(Order.asc("colCreateDate"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			collaborations = criteria.list();
			for(CrmCollaboration col : collaborations){
				col.setCreateDate(new DateTimeOperation().reverseLocalDateTimeToString(col.getColCreateDate()));
			}
			return collaborations;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public CrmCollaboration findCollaborationById(int collapId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCollaboration.class);
			criteria.add(Restrictions.eq("colId", collapId));
			return (CrmCollaboration)criteria.uniqueResult();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

}
