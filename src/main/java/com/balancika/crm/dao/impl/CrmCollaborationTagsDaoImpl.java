package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCollaborationTagsDao;
import com.balancika.crm.model.CrmCollaborationTags;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmCollaborationTagsDaoImpl implements CrmCollaborationTagsDao{
	
	@Override
	public boolean insertCollaborationTags(List<CrmCollaborationTags> tags) {
		Session session = new HibernateSessionFactory().getSessionFactory(tags.get(0).getMeDataSource()).openSession();
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
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.clear();
			session.close();
			e.printStackTrace();
		} 
		return false;
	}

	@Override
	public boolean deleteCollaborationTagsByCollaborationId(int collapId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			session.beginTransaction();
			CrmCollaborationTags tag = new CrmCollaborationTags();
			tag.setTagId(collapId);
			session.delete(tag);
			session.getTransaction().commit();
			session.clear();
			session.close();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.clear();
			session.close();
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCollaborationTags> listCollaborationTags(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
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
		}
		return null;
	}

}
