package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmRoleDao;
import com.balancika.crm.model.CrmRole;
import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository("CrmRoleDao")
public class CrmRoleDaoImpl extends CrmIdGenerator implements CrmRoleDao{

	private Session session;
	
	private SessionFactory sessionFactory;
	
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
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
			role.setCreateDate(LocalDateTime.now());
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
			
			SQLQuery query = session.createSQLQuery("CALL crm_delete_role_detail_by_id()");
			query.executeUpdate();
			
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
			SQLQuery query = session.createSQLQuery("CALL crm_delete_role_by_id(:roleId)");
			query.setParameter("roleId", role.getRoleId());
			query.executeUpdate();
	
			return true;
		} catch (Exception e) {
			//session.getTransaction().rollback();
			e.printStackTrace();
			return false;
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		
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

	@Override
	public CrmRole findRoleByUsername(String username, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmUser.class);
			criteria.add(Restrictions.eq("userID", username));
			CrmUser user = (CrmUser)criteria.uniqueResult();
			CrmRole role = findRoleById(user.getRole().getRoleId(), dataSource);
			return role;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			//session.clear();
			//session.close();
			sessionFactory.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> findRoleDetailsByRoleId(String roleId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL crmFindRoleById(:roleId)");
			query.setParameter("roleId", roleId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
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
	public List<Object> findRoleMaster(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL crm_list_role_master()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
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
