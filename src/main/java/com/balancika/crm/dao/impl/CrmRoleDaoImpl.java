package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmRoleDao;
import com.balancika.crm.model.CrmRole;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository("CrmRoleDao")
public class CrmRoleDaoImpl extends CrmIdGenerator implements CrmRoleDao{

	private Session session;
	
	private SessionFactory sessionFactory;
	
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean isInsertedRole(CrmRole role) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(role.getMeDataSource()));
		session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			role.setRoleId(IdAutoGenerator("RM",role.getMeDataSource()));
			role.setRoleName(role.getRoleName().toUpperCase());
			session.save(role);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean isUpdatedRole(CrmRole role) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(role.getMeDataSource()));
		session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(role);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean isDeletedRole(CrmRole role) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(role.getMeDataSource()));
		session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(role);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public CrmRole findRoleById(String roleId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
		try {
			return (CrmRole) session.get(CrmRole.class, roleId);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmRole> listAllRoles(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmRole.class);
			criteria.add(Restrictions.eq("roleStatus", 1));
			criteria.add(Restrictions.ne("roleName", "CRM_ADMIN"));
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
}
