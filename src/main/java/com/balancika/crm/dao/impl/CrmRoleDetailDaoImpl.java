package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmRoleDetailDao;
import com.balancika.crm.model.CrmRoleDetail;

@Repository
public class CrmRoleDetailDaoImpl implements CrmRoleDetailDao{
	
	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertRoleDetail(CrmRoleDetail roleDetail) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.save(roleDetail);
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
	public boolean updateRoleDetail(CrmRoleDetail roleDetail) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(roleDetail);
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
	public boolean deleteRoleDetail(int roleDetailId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmRoleDetail roleDetail = new CrmRoleDetail();
			roleDetail.setRoleDetailId(roleDetailId);
			session.delete(roleDetail);
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
	public List<CrmRoleDetail> listRoleDetails() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmRoleDetail.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmRoleDetail findRoleDetailById(int roleDetailId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmRoleDetail.class);
		criteria.add(Restrictions.eq("roleDetailId", roleDetailId));
		return (CrmRoleDetail)criteria.uniqueResult();
	}

	@Override
	public Object findRoleDetailsByUsername(String username,String moduleId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL findRoleDetailsByUsername(:username, :moduleId)");
		query.setParameter("username", username);
		query.setParameter("moduleId", moduleId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

}
