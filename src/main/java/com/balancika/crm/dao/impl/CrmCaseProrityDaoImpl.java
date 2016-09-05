package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCasePriorityDao;
import com.balancika.crm.model.CrmCasePriority;

@Repository
public class CrmCaseProrityDaoImpl implements CrmCasePriorityDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertCasePriority(CrmCasePriority casePriority) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(casePriority);
			session.getTransaction().commit();
			return true;
		} catch(HibernateException e){
			session.getTransaction().rollback();
		} finally{
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateCasePriority(CrmCasePriority casePriority) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(casePriority);
			session.getTransaction().commit();
			return true;
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public String deleteCasePriority(int priorityId) {
		Session session = transactionManager.getSessionFactory().openSession();
		CrmCasePriority casePriority = new CrmCasePriority();
		try {
			session.beginTransaction();
			casePriority.setPriorityId(priorityId);
			session.delete(casePriority);
			session.getTransaction().commit();
			return "OK";
		} catch (ConstraintViolationException ex){
			session.getTransaction().rollback();
			ex.printStackTrace();
			return "FOREIGN_KEY_CONSTRAIN";
		} catch(HibernateException e){
			session.getTransaction().rollback();
			e.printStackTrace();
			return "FAILED";
		}finally{
			session.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCasePriority> listCasePriorities() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmCasePriority.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmCasePriority findCasePriorityById(int priorityId) {
		return (CrmCasePriority) transactionManager.getSessionFactory().getCurrentSession().get(CrmCasePriority.class, priorityId);
	}

}
