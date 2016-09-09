package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmTaskDao;
import com.balancika.crm.model.CrmTask;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmTaskDaoImpl extends CrmIdGenerator implements CrmTaskDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertTask(CrmTask task) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			task.setTaskId(IdAutoGenerator("AC_TA"));
			session.save(task);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateTask(CrmTask task) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(task);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteTask(String taskId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmTask task = new CrmTask();
			task.setTaskId(taskId);
			session.delete(task);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmTask> listTasks() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listCrmTasks()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public Object findTaskById(String taskId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL findCrmTaskById(:taskId)");
		query.setParameter("taskId", taskId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

	@Override
	public CrmTask findTaskDetailsById(String taskId) {
		return (CrmTask)transactionManager.getSessionFactory().getCurrentSession().get(CrmTask.class, taskId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmTask> listTasksRelatedToLead(String leadId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listTasksRelatedToLead(:leadId)");
				query.setParameter("leadId", leadId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmTask> listTasksRelatedToOpportunity(String opId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listTasksRelatedToOpportunity(:opId)");
				query.setParameter("opId", opId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

}
