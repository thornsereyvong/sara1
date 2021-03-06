package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCollaborationDao;
import com.balancika.crm.dao.CrmLikeDao;
import com.balancika.crm.model.CrmCollaboration;
import com.balancika.crm.model.CrmCollaborationDetails;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmCollaborationDaoImpl implements CrmCollaborationDao{
	
	@Autowired
	private CrmLikeDao likeDao;
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertCollaboration(CrmCollaboration collaboration) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(collaboration.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			collaboration.setColCreateDate(LocalDateTime.now());
			collaboration.setColOwn("true");
			session.save(collaboration);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.clear();
			session.close();
			sessionFactory.close();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean updateCollaboration(CrmCollaboration collaboration) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(collaboration.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(collaboration);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.clear();
			session.close();
			sessionFactory.close();
			e.printStackTrace();
		}
		return false;
	}

	@Override
	public boolean deleteCollaboration(CrmCollaboration collaboration) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(collaboration.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.delete(collaboration);
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			session.getTransaction().rollback();
			session.clear();
			session.close();
			sessionFactory.close();
			e.printStackTrace();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCollaboration> listCollaborations(String moduleId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		List<CrmCollaboration> collaborations = new ArrayList<CrmCollaboration>();
		try {
			Criteria criteria = session.createCriteria(CrmCollaboration.class);
			criteria.add(Restrictions.eq("colRelatedToModuleId", moduleId));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
			criteria.addOrder(Order.desc("colCreateDate"));
			collaborations = criteria.list();
			for(int i = 0; i < collaborations.size(); i++){
				collaborations.get(i).setCreateDate(new DateTimeOperation().reverseLocalDateTimeToString(collaborations.get(i).getColCreateDate()));
				for(CrmCollaborationDetails details : collaborations.get(i).getDetails()){
					details.setFormatCreateDate(new DateTimeOperation().reverseLocalDateTimeToString(details.getCreateDate()));
				}
				collaborations.get(i).setLike(likeDao.countLike(collaborations.get(i).getColId(), dataSource));
				collaborations.get(i).setCheckLike(likeDao.checkUserLike(collaborations.get(i).getColUser(),collaborations.get(i).getColId(), dataSource));
			}
			return collaborations;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@Override
	public CrmCollaboration findCollaborationById(int collapId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCollaboration.class);
			criteria.add(Restrictions.eq("colId", collapId));
			CrmCollaboration collaboration = (CrmCollaboration)criteria.uniqueResult();
			collaboration.setCreateDate(new DateTimeOperation().reverseLocalDateTimeToString(collaboration.getColCreateDate()));
			return collaboration;
		} catch (HibernateException e) {
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
	public List<Map<String, Object>> listAllCollaboration(String username, String moduleType, String moduleId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listAllCollaborationRelatedToModule(:username, :moduleType, :moduleId)");
			query.setParameter("username", username);
			query.setParameter("moduleType", moduleType);
			query.setParameter("moduleId", moduleId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<Map<String, Object>> coList  = query.list();
			return coList;
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

}
