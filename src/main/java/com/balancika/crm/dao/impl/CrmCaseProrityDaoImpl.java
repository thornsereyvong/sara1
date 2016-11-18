package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCasePriorityDao;
import com.balancika.crm.model.CrmCasePriority;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmCaseProrityDaoImpl implements CrmCasePriorityDao {
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertCasePriority(CrmCasePriority casePriority) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(casePriority.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(casePriority);
			session.getTransaction().commit();
			return true;
		} catch(HibernateException e){
			session.getTransaction().rollback();
		} finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateCasePriority(CrmCasePriority casePriority) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(casePriority.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(casePriority);
			session.getTransaction().commit();
			return true;
		}catch(HibernateException e){
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public String deleteCasePriority(CrmCasePriority casePriority) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(casePriority.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
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
			session.clear();
			session.close();
			sessionFactory.close();
		}
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCasePriority> listCasePriorities(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCasePriority.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@Override
	public CrmCasePriority findCasePriorityById(int priorityId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			return (CrmCasePriority) session.get(CrmCasePriority.class, priorityId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

}
