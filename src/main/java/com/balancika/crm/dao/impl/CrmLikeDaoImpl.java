package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmLikeDao;
import com.balancika.crm.model.CrmLike;

@Repository
public class CrmLikeDaoImpl implements CrmLikeDao{

	@Autowired
	private HibernateTransactionManager transactionManager; 
	
	@Override
	public boolean insertLike(CrmLike like) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(like);
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
	public boolean deleteLike(String username) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_user_like_collaboration WHERE LK_UserName = :username");
			query.setParameter("username", username);
			session.getTransaction().commit();
			if(query.executeUpdate() > 0){
				session.close();
				return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@Override
	public Integer countLike(int collapId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(CrmLike.class);
			criteria.setProjection(Projections.property("likeId"));
			criteria.add(Restrictions.eq("collapId", collapId));
			criteria.setProjection(Projections.rowCount());
			return ((Number)criteria.uniqueResult()).intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public boolean checkUserLike(String username) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			Criteria criteria = session.createCriteria(CrmLike.class);
			criteria.setProjection(Projections.property("likeId"));
			criteria.add(Restrictions.eq("LK_UserName", username));
			criteria.setProjection(Projections.rowCount());
			if(((Number)criteria.uniqueResult()).intValue() > 0){
				return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return false;
	}
}
