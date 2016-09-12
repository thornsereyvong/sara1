package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCollaborationDao;
import com.balancika.crm.dao.CrmCollaborationTagsDao;
import com.balancika.crm.dao.CrmLikeDao;
import com.balancika.crm.model.CrmCollaboration;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmCollaborationDaoImpl implements CrmCollaborationDao{
	
	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Autowired
	private CrmCollaborationTagsDao tagsDao;
	
	@Autowired
	private CrmLikeDao likeDao;

	@Override
	public boolean insertCollaboration(CrmCollaboration collaboration) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			collaboration.setColCreateDate(LocalDateTime.now());
			collaboration.setColOwn("true");
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
	public List<CrmCollaboration> listCollaborations(String moduleId) {
		Session session = transactionManager.getSessionFactory().openSession();
		List<CrmCollaboration> collaborations = new ArrayList<CrmCollaboration>();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCollaboration.class);
			criteria.add(Restrictions.eq("colRelatedToModuleId", moduleId));
			criteria.addOrder(Order.asc("colCreateDate"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			collaborations = criteria.list();
			for(CrmCollaboration col : collaborations){
				col.setCreateDate(new DateTimeOperation().reverseLocalDateTimeToString(col.getColCreateDate()));
				col.setLike(likeDao.countLike(col.getColId()));
				col.setCheckLike(likeDao.checkUserLike(col.getColUser()));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Map<String, Object>> listAllCollaboration(String username, String moduleType, String moduleId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listAllCollaborationRelatedToModule(:username, :moduleType, :moduleId)");
			query.setParameter("username", username);
			query.setParameter("moduleType", moduleType);
			query.setParameter("moduleId", moduleId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<Map<String, Object>> coList  = query.list();
			return coList;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

}
