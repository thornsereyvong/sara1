package com.balancika.crm.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmLeadProjectDao;
import com.balancika.crm.model.CrmLeadProject;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmLeadProjectDaoImpl implements CrmLeadProjectDao{
	
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean addLeadProject(CrmLeadProject leadProject) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(leadProject.getDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(leadProject);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateLeadProject(CrmLeadProject leadProject) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(leadProject.getDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(leadProject);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteLeadProject(CrmLeadProject leadProject) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(leadProject.getDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			this.deleteOpportunityLeadProject(leadProject.getId(), leadProject.getDataSource());
			session.delete(leadProject);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public CrmLeadProject findLeadProjectById(int id, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmLeadProject.class);
			criteria.add(Restrictions.eq("id", id));
			session.getTransaction().commit();
			return (CrmLeadProject)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return new CrmLeadProject();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLeadProject> listLeadProjects(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmLeadProject.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			session.getTransaction().commit();
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return new ArrayList<CrmLeadProject>();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmLeadProject> listCustomFieldLeadProject(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmLeadProject.class);
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("id"), "id")
					.add(Projections.property("name"), "name"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			session.getTransaction().commit();
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return new ArrayList<CrmLeadProject>();
	}
	
	private void deleteOpportunityLeadProject(int id, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_opportunity_lead_project WHERE LPID = :id ;");
			query.setParameter("id", id);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
	}

}
