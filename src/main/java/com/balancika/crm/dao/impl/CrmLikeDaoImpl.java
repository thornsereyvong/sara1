package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
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
	public boolean deleteLike(int likeId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmLike like = new CrmLike();
			like.setLikeId(likeId);
			session.delete(like);
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
	public Integer countLike(int collapId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
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

}
