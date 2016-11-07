package com.balancika.crm.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmLikeDao;
import com.balancika.crm.model.CrmLike;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmLikeDaoImpl implements CrmLikeDao{
	
	@Override
	public boolean insertLike(CrmLike like) {
		Session session = new HibernateSessionFactory().getSessionFactory(like.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(like);
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
	public boolean deleteLike(int collapId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_user_like_collaboration WHERE LK_CBID = :collapId");
			query.setParameter("collapId", collapId);
			session.getTransaction().commit();
			if(query.executeUpdate() > 0){
				session.clear();
				session.close();
				return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public Integer countLike(int collapId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmLike.class);
			criteria.setProjection(Projections.property("likeId"));
			criteria.add(Restrictions.eq("collapId", collapId));
			criteria.setProjection(Projections.rowCount());
			return ((Number)criteria.uniqueResult()).intValue();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public boolean checkUserLike(String username, int postId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmLike.class);
			criteria.add(Restrictions.eq("username", username));
			criteria.add(Restrictions.eq("collapId", postId));
			criteria.setProjection(Projections.rowCount());
			if(((Number)criteria.uniqueResult()).intValue() > 0){
				return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}
}
