package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmRoleDetailDao;
import com.balancika.crm.model.CrmRoleDetail;
import com.balancika.crm.model.MeDataSource;

@Repository
public class CrmRoleDetailDaoImpl implements CrmRoleDetailDao{
	
	@Override
	public boolean insertRoleDetail(CrmRoleDetail roleDetail) {
		Session session = new HibernateSessionFactory().getSessionFactory(roleDetail.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.save(roleDetail);
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

	@Override
	public boolean updateRoleDetail(CrmRoleDetail roleDetail) {
		Session session = new HibernateSessionFactory().getSessionFactory(roleDetail.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			session.update(roleDetail);
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

	@Override
	public boolean deleteRoleDetail(CrmRoleDetail roleDetail) {
		Session session = new HibernateSessionFactory().getSessionFactory(roleDetail.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
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
	public List<CrmRoleDetail> listRoleDetails(MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmRoleDetail.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public CrmRoleDetail findRoleDetailById(int roleDetailId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(CrmRoleDetail.class);
			criteria.add(Restrictions.eq("roleDetailId", roleDetailId));
			return (CrmRoleDetail)criteria.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public Object findRoleDetailsByUsername(String username,String moduleId, MeDataSource dataSource) {
		Session session = new HibernateSessionFactory().getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findRoleDetailsByUsername(:username, :moduleId)");
			query.setParameter("username", username);
			query.setParameter("moduleId", moduleId);
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

}
