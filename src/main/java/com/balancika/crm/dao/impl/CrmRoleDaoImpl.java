package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmRoleDao;
import com.balancika.crm.model.CrmRole;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository("CrmRoleDao")
public class CrmRoleDaoImpl extends CrmIdGenerator implements CrmRoleDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	private Session session;
	
	@Override
	public boolean isInsertedRole(CrmRole role) {
		session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			role.setRoleId(IdAutoGenerator("RM"));
			role.setRoleName(role.getRoleName().toUpperCase());
			session.save(role);
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
	public boolean isUpdatedRole(CrmRole role) {
		session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(role);
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
	public boolean isDeletedRole(String roleId) {
		session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmRole role = new CrmRole();
			role.setRoleId(roleId);
			session.delete(role);
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
	public CrmRole findRoleById(String roleId) {
		return (CrmRole) transactionManager.getSessionFactory().getCurrentSession().get(CrmRole.class, roleId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmRole> listAllRoles() {
		Criteria criteria = transactionManager.getSessionFactory().getCurrentSession().createCriteria(CrmRole.class);
		criteria.add(Restrictions.eq("roleStatus", 1));
		criteria.add(Restrictions.ne("roleName", "CRM_ADMIN"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
}
