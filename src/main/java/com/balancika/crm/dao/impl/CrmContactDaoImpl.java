package com.balancika.crm.dao.impl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmContactDao;
import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.CrmContact;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmContactDaoImpl extends CrmIdGenerator implements CrmContactDao {
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertContact(CrmContact contact) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(contact.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			contact.setConID(IdAutoGenerator("CO", contact.getMeDataSource()));
			contact.setConCreateDate(new Date());
			session.save(contact);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateContact(CrmContact contact) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(contact.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(contact);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteContact(CrmContact contact) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(contact.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_contact WHERE CO_ID = :conId");
			query.setParameter("conId", contact.getConID());
			if (query.executeUpdate() > 0) {
				session.getTransaction().commit();
				return true;
			}
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmContact> listContacts(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCrmContacts()");
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
	public Object findContactById(String conId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL findCrmContactById(:conId)");
			query.setParameter("conId", conId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
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
	public CrmContact findContactDetailsById(String conId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			return (CrmContact) session.get(CrmContact.class, conId);
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
	public List<Object> listContactRelatedToModule(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmContact.class);
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("conID"), "conID")
					.add(Projections.property("conSalutation"), "conSalutation")
					.add(Projections.property("conFirstname"),"conFirstname")
					.add(Projections.property("conLastname"), "conLastname"));
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

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listParentOfContact(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmContact.class);
			criteria.setProjection(Projections.projectionList()
								.add(Projections.property("conID"), "conID")
								.add(Projections.property("conSalutation"), "conSalutation")
								.add(Projections.property("conFirstname"), "conFirstname")
								.add(Projections.property("conLastname"), "conLastname"));
			criteria.add(Restrictions.eqOrIsNull("conReportTo", null));
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
	public Map<String, Object> viewContact(String conId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CASES", getCasesRelatedToContact(conId, dataSource));
		map.put("CONTACT", findContactById(conId, dataSource));
		map.put("OPPORTUNITIES", getOpportunityRelatedToContact(conId, dataSource));
		return map;
	}
	
	@SuppressWarnings("unchecked")
	private List<CrmOpportunity> getOpportunityRelatedToContact(String conId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listOpportunitiesRelatedToContact(:conId)");
			query.setParameter("conId",conId);
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
	private List<CrmCase> getCasesRelatedToContact(String conId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCase.class, "case").createAlias("case.contact", "con");
			criteria.add(Restrictions.eq("con.conID", conId));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("caseId"), "caseId")
					.add(Projections.property("createDate"), "createDate")
					.add(Projections.property("subject"), "subject")
					.add(Projections.property("status"), "status")
					.add(Projections.property("priority"), "priority"));
			criteria.setResultTransformer(Transformers.aliasToBean(CrmCase.class));
			List<CrmCase> cases = criteria.list();
			for(CrmCase cs : cases){
				cs.setConvertCreateDate(new DateTimeOperation().reverseLocalDateTimeToString(cs.getCreateDate()));
			}
			return cases;
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
	public List<CrmContact> listSomeFieldsOfContact(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmContact.class, "con");
			criteria.setProjection(Projections.projectionList()
								.add(Projections.property("conID"), "conID")
								.add(Projections.property("conSalutation"), "conSalutation")
								.add(Projections.property("conFirstname"), "conFirstname")
								.add(Projections.property("conLastname"), "conLastname"));
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
