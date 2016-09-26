package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCollaborationTagsDao;
import com.balancika.crm.model.CrmCollaborationTags;

@Repository
public class CrmCollaborationTagsDaoImpl implements CrmCollaborationTagsDao{
	
	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertCollaborationTags(List<CrmCollaborationTags> tags) {
		Session session = transactionManager.getSessionFactory().openSession();
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
			session.close();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCollaborationTagsByCollaborationId(int collapId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmCollaborationTags tag = new CrmCollaborationTags();
			tag.setTagId(collapId);
			session.delete(tag);
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCollaborationTags> listCollaborationTags() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCollaborationTags.class);
			return criteria.list();
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} 
		return null;
	}

}
