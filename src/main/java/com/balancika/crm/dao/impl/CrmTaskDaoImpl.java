package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmTaskDao;
import com.balancika.crm.model.CrmTask;
import com.balancika.crm.utilities.DateTimeOperation;
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
			DateTimeOperation toLocalDateTime = new DateTimeOperation();
			task.setTaskId(IdAutoGenerator("AC_TA"));
			task.setTaskStartDate(toLocalDateTime.convertStringToLocalDateTime(task.getStartDate()));
			task.setTaskDueDate(toLocalDateTime.convertStringToLocalDateTime(task.getDueDate()));
			task.setTaskCreateDate(LocalDateTime.now());
			session.save(task);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
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
			DateTimeOperation toLocalDateTime = new DateTimeOperation();
			task.setTaskStartDate(toLocalDateTime.convertStringToLocalDateTime(task.getStartDate()));
			task.setTaskDueDate(toLocalDateTime.convertStringToLocalDateTime(task.getDueDate()));
			session.update(task);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			e.printStackTrace();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmTask> listTasksRelatedToModule(String moduleId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmTask.class);
			criteria.add(Restrictions.eq("taskRelatedToId", moduleId));
			List<CrmTask> tasks = criteria.list();
			for(CrmTask task : tasks){
				task.setStartDate(new DateTimeOperation().reverseLocalDateTimeToString(task.getTaskStartDate()));
				task.setDueDate(new DateTimeOperation().reverseLocalDateTimeToString(task.getTaskDueDate()));
			}
			return tasks;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

}
