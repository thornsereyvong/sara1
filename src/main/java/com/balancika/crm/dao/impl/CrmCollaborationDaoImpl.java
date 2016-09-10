package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCollaborationDao;
import com.balancika.crm.model.CrmCollaboration;
import com.balancika.crm.services.CrmCollaborationTagsService;

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
			session.persist(collaboration);
			if(tagsService.insertCollaborationTags(collaboration.getTags()) == true){
				session.flush();
				session.getTransaction().commit();
				session.close();
				return true;
			}else{
				session.getTransaction().rollback();
				session.close();
			}
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.close();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCollaboration(CrmCollaboration collaboration) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCollaboration(int colId) {
		// TODO Auto-generated method stub
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCollaboration> listCollaborations() {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCollaboration.class);
			criteria.setResultTransformer(Transformers.aliasToBean(CrmCollaboration.class));
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.close();
		}
		return null;
	}

	@Override
	public CrmCollaboration findCollaborationById(int collapId) {
		// TODO Auto-generated method stub
		return null;
	}

}
