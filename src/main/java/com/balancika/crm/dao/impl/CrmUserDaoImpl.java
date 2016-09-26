package com.balancika.crm.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmUserDao;
import com.balancika.crm.model.CrmUser;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository("CrmUserDao")
@Transactional
public class CrmUserDaoImpl extends CrmIdGenerator implements CrmUserDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Session session = null;
	
	@Override
	public boolean isInserted(CrmUser user) {
		session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			user.setUserID(IdAutoGenerator("UM"));	
			user.setPassword(passwordEncoder.encode(user.getPassword()));
			session.save(user);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean isUpdated(CrmUser user) {
		session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(user);
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
	public boolean isDeleted(String userId) {
		session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmUser user = new CrmUser();
			user.setUserID(userId);
			session.delete(user);
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
	public CrmUser findUserByUsername(String username) {
		session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmUser.class);
		criteria.add(Restrictions.eq("username", username));	
		CrmUser user = (CrmUser)criteria.uniqueResult();
		if(user != null){
			Hibernate.initialize(user.getRole());
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmUser> listAllUsers() {
		session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmUser.class);
		criteria.add(Restrictions.eq("status", 1));
		criteria.addOrder(Order.desc("userID"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		//criteria.setProjection(Projections.groupProperty(""));
		return criteria.list();
	}

	@Override
	public CrmUser findUserById(String userId) {
		
		return (CrmUser) transactionManager.getSessionFactory().getCurrentSession().get(CrmUser.class, userId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmUser> listSubordinateUserByUsername(String username) {
		session = transactionManager.getSessionFactory().getCurrentSession();
		
		DetachedCriteria subCriteria = DetachedCriteria.forClass(CrmUser.class);
		subCriteria.add(Restrictions.eq("username", username));
		subCriteria.setProjection((Projections.projectionList().add(Projections.property("userID"), "userID")))
				   .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		Criteria criteria = session.createCriteria(CrmUser.class, "user");
		criteria.setProjection(Projections.projectionList().add(Projections.property("user.userID"), "userID")
				   											.add(Projections.property("user.username"), "username"));
		criteria.add(Restrictions.or(Subqueries.propertyEq("parentID", subCriteria), Restrictions.eq("username", username)));
		criteria.addOrder(Order.asc("userID")).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return criteria.list();
	}

	@Override
	public CrmUser webLogin(String username) {
		session = transactionManager.getSessionFactory().getCurrentSession();	
		Criteria criteria = session.createCriteria(CrmUser.class);
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("status", 1));
		CrmUser user = (CrmUser)criteria.uniqueResult();
		if(user != null){
				return user;
			}
		return null;
	}

	@Override
	public String checkChildOfUser(String username) {
		session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("CALL checkChildOfUser(:username)");
			query.setParameter("username", username);
			if(((Number)query.uniqueResult()).intValue() > 0){
				return "EXIST";
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return "NOT_EXIST";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listAllUsernameAndId() {
		session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmUser.class);
		criteria.setProjection(Projections.projectionList().add(Projections.property("userID"), "userID").add(Projections.property("username"), "username"));
		criteria.add(Restrictions.eq("status", 1));
		criteria.addOrder(Order.asc("userID"));
		criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return criteria.list();
	}
}
