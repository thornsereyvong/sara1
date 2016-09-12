package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCollaborationDetailsDao;
import com.balancika.crm.model.CrmCollaborationDetails;

@Repository
public class CrmCollaborationDetailsDaoImpl implements CrmCollaborationDetailsDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertCollaborationDetails(CrmCollaborationDetails details) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			details.setColDelCreateDate(LocalDateTime.now());
			session.save(details);
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
	public boolean updateCollaborationDetails(CrmCollaborationDetails details) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteCollaborationDetails(int detailsId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<CrmCollaborationDetails> listCollaborationDetails() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public CrmCollaborationDetails findCollaborationDetailsById(int detailsId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
