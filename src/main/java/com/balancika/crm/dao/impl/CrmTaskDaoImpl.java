package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmTaskDao;
import com.balancika.crm.model.CrmTask;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.DateTimeOperation;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmTaskDaoImpl extends CrmIdGenerator implements CrmTaskDao{

	@Override
	public boolean insertTask(CrmTask task) {
		Session session = HibernateSessionFactory.getSessionFactory(task.getMeDataSource()).openSession();
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
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateTask(CrmTask task) {
		Session session = HibernateSessionFactory.getSessionFactory(task.getMeDataSource()).openSession();
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
			session.clear();
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteTask(CrmTask task) {
		Session session = HibernateSessionFactory.getSessionFactory(task.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.delete(task);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmTask> listTasks(MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmTasks()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public Object findTaskById(String taskId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findCrmTaskById(:taskId)");
			query.setParameter("taskId", taskId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		
	}

	@Override
	public CrmTask findTaskDetailsById(String taskId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			return (CrmTask)session.get(CrmTask.class, taskId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmTask> listTasksRelatedToLead(String leadId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listTasksRelatedToLead(:leadId)");
				query.setParameter("leadId", leadId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmTask> listTasksRelatedToOpportunity(String opId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listTasksRelatedToOpportunity(:opId)");
				query.setParameter("opId", opId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmTask> listTasksRelatedToModule(String moduleId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listTasksRelatedToModule(:moduleId)");
			query.setParameter("moduleId", moduleId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

}
