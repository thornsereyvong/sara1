package com.balancika.crm.dao.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.criterion.Subqueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmUserDao;
import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.PasswordEncrypt;

@Repository("CrmUserDao")
@Transactional
public class CrmUserDaoImpl extends CrmIdGenerator implements CrmUserDao{

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	private Session session = null;
	
	private SessionFactory sessionFactory;
	
	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean isInserted(CrmUser user) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(user.getDataSource()));
		session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			//user.setUserID(IdAutoGenerator("UM", user.getDataSource()));	
			user.setPassword(new PasswordEncrypt().BalEncrypt(user.getPassword()));
			session.save(user);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean isUpdated(CrmUser user) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(user.getDataSource()));
		session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(user);
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
	public boolean isDeleted(CrmUser user) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(user.getDataSource()));
		session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(user);
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
	public CrmUser findUserByUsername(String username, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmUser.class);
			criteria.add(Restrictions.eq("username", username));	
			CrmUser result = (CrmUser)criteria.uniqueResult();
			if(result != null){
				Hibernate.initialize(result.getRole());
			}
			return result;
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
	public List<CrmUser> listAllUsers(MeDataSource meDataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(meDataSource));
		session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmUser.class, "user").createAlias("user.role", "role");
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("userID"),"userID")
					.add(Projections.property("username"),"username")
					.add(Projections.property("role.roleId"),"roleId")
					.add(Projections.property("role.roleName"),"roleName")
					.add(Projections.property("role.createDate"),"createDate"));
			criteria.add(Restrictions.eq("status", 1));
			criteria.add(Restrictions.ne("role.roleName", "CRM_ADMIN"));
			criteria.addOrder(Order.desc("userID"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
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
	public CrmUser findUserById(String userID, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			CrmUser result = (CrmUser) session.get(CrmUser.class, userID);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return result;
		} catch (Exception e) {
			e.printStackTrace();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmUser> listSubordinateUserByUsername(String username, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
		try {
			DetachedCriteria subCriteria = DetachedCriteria.forClass(CrmUser.class);
			subCriteria.add(Restrictions.eq("username", username));
			subCriteria.setProjection((Projections.projectionList().add(Projections.property("userID"), "userID")))
					   .setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			Criteria criteria = session.createCriteria(CrmUser.class, "user");
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("user.userID"), "userID")
					.add(Projections.property("user.username"), "username"));
			criteria.add(Restrictions.or(Subqueries.propertyEq("parentID", subCriteria), Restrictions.eq("username", username)));
			criteria.addOrder(Order.asc("userID")).setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return criteria.list();
		}catch(Exception e){
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@Override
	public CrmUser webLogin(CrmUser user) {
		try {
			setSessionFactory(new HibernateSessionFactory().getSessionFactory(user.getDataSource()));
			session = getSessionFactory().openSession();
			Criteria criteria = session.createCriteria(CrmUser.class);
			criteria.add(Restrictions.eq("username", user.getUsername()));
			criteria.add(Restrictions.eq("status", 1));
			CrmUser result = (CrmUser)criteria.uniqueResult();
			if(result != null){
					return result;
				}
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
	public String checkChildOfUser(String username, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
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
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return "NOT_EXIST";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listAllUsernameAndId(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmUser.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("userID"), "userID").add(Projections.property("username"), "username"));
			criteria.add(Restrictions.eq("status", 1));
			criteria.addOrder(Order.asc("userID"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
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