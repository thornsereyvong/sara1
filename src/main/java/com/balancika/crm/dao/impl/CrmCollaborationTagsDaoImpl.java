package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCollaborationTagsDao;
import com.balancika.crm.model.CrmCollaborationTags;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmCollaborationTagsDaoImpl implements CrmCollaborationTagsDao{
	
	private SessionFactory sessionFactory;
	
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertCollaborationTags(List<CrmCollaborationTags> tags, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			for(int i = 0; i < tags.size(); i++){
				session.save(tags.get(i));
				if(i % 20 == 0){
					session.flush();
			        session.clear();
				}
			}
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.clear();
			session.close();
			sessionFactory.close();
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public boolean deleteCollaborationTagsByCollaborationId(int collapId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmCollaborationTags tag = new CrmCollaborationTags();
			tag.setTagId(collapId);
			session.delete(tag);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.clear();
			session.close();
			sessionFactory.close();
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCollaborationTags> listCollaborationTags(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCollaborationTags.class);
			return criteria.list();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

}
