package com.balancika.crm.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.ConstraintViolationException;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmContactDao;
import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.CrmContact;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmContactDaoImpl extends CrmIdGenerator implements CrmContactDao {

	@Autowired
	private HibernateTransactionManager transactionManager;

	@Override
	public boolean insertContact(CrmContact contact) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			contact.setConID(IdAutoGenerator("CO"));
			session.save(contact);
			session.getTransaction().commit();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateContact(CrmContact contact) {
		Session session = transactionManager.getSessionFactory().openSession();
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
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteContact(String conId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_contact WHERE CO_ID = :conId");
			query.setParameter("conId", conId);
			if (query.executeUpdate() > 0) {
				session.getTransaction().commit();
				return true;
			}
		} catch (HibernateException e) {
			session.getTransaction().rollback();
		} catch (ConstraintViolationException e) {
			session.getTransaction().rollback();
		} finally {
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmContact> listContacts() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listCrmContacts()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public Object findContactById(String conId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL findCrmContactById(:conId)");
		query.setParameter("conId", conId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.uniqueResult();
	}

	@Override
	public CrmContact findContactDetailsById(String conId) {
		return (CrmContact) transactionManager.getSessionFactory().getCurrentSession().get(CrmContact.class, conId);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listContactRelatedToModule() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmContact.class);
		criteria.setProjection(Projections.projectionList()
				.add(Projections.property("conID"), "conID")
				.add(Projections.property("conFirstname"),"conFirstname")
				.add(Projections.property("conLastname"), "conLastname"));
		criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return criteria.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listParentOfContact() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmContact.class);
		criteria.setProjection(Projections.projectionList()
							.add(Projections.property("conID"), "conID")
							.add(Projections.property("conSalutation"), "conSalutation")
							.add(Projections.property("conFirstname"), "conFirstname")
							.add(Projections.property("conLastname"), "conLastname"));
		criteria.add(Restrictions.eqOrIsNull("conReportTo", null));
		criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return criteria.list();
	}

	@Override
	public Map<String, Object> viewContact(String conId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("CASES", getCasesRelatedToContact(conId));
		map.put("CONTACT", findContactById(conId));
		map.put("OPPORTUNITIES", getOpportunityRelatedToContact(conId));
		return map;
	}
	
	@SuppressWarnings("unchecked")
	private List<CrmOpportunity> getOpportunityRelatedToContact(String conId){
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("CALL listOpportunitiesRelatedToContact(:conId)");
			query.setParameter("conId",conId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<CrmCase> getCasesRelatedToContact(String conId){
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
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
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmContact> listSomeFieldsOfContact() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmContact.class);
		criteria.setProjection(Projections.projectionList()
							.add(Projections.property("conID"), "conID")
							.add(Projections.property("conSalutation"), "conSalutation")
							.add(Projections.property("conFirstname"), "conFirstname")
							.add(Projections.property("conLastname"), "conLastname"));
		criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return criteria.list();
	}
}
