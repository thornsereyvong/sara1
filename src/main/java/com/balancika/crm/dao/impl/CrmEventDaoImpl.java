package com.balancika.crm.dao.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmEventDao;
import com.balancika.crm.model.CrmEvent;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.DateTimeOperation;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmEventDaoImpl extends CrmIdGenerator implements CrmEventDao {
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertEvent(CrmEvent event) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(event.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			event.setEvId(IdAutoGenerator("AC_EV", event.getMeDataSource()));
			DateTimeOperation toLocalDateTime = new DateTimeOperation();
			event.setEvStartDate(toLocalDateTime.convertStringToLocalDateTime(event.getStartDate()));
			event.setEvEndDate(toLocalDateTime.convertStringToLocalDateTime(event.getEndDate()));
			event.setEvCreateDate(LocalDateTime.now());
			session.save(event);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateEvent(CrmEvent event) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(event.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			DateTimeOperation toLocalDateTime = new DateTimeOperation();
			event.setEvStartDate(toLocalDateTime.convertStringToLocalDateTime(event.getStartDate()));
			event.setEvEndDate(toLocalDateTime.convertStringToLocalDateTime(event.getEndDate()));
			session.update(event);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteEnvent(CrmEvent event) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(event.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_event WHERE EV_ID = :evId");
			query.setParameter("evId", event.getEvId());
			if (query.executeUpdate() > 0) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmEvent> listEvents(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmEvents()");
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

	@Override
	public Object findEventById(String evId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findCrmEventById(:evId)");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			query.setParameter("evId", evId);
			return query.uniqueResult();
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
	public CrmEvent findEventDetailsById(String evId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmEvent.class);
			criteria.add(Restrictions.eq("evId", evId));
			return (CrmEvent) criteria.uniqueResult();
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
	public List<CrmEvent> listEventsRelatedToLead(String leadId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listEventsRelatedToLead(:leadId)");
				query.setParameter("leadId", leadId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
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
	public List<CrmEvent> listEventsRelatedToOpportunity(String opId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listEventsRelatedOpportuntiy(:opId)");
				query.setParameter("opId", opId);
				query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
				return query.list();
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
	public List<CrmEvent> listEventsRelatedToModule(String moduleId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listEventsRelatedToModule(:moduleId)");
			query.setParameter("moduleId", moduleId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
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
