package com.balancika.crm.dao.impl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.CrmContact;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.CrmCustomerDetails;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.PriceCode;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class CrmCustomerDaoImpl extends CrmIdGenerator implements CrmCustomerDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertCustomer(CrmCustomer customer) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String custId = ameIdAutoGenerator("CUST");
			customer.setCustID(custId);
			session.persist(customer);
			if(customer.getCustDetails() != null){
				for(int i = 0; i < customer.getCustDetails().size(); i++){
					customer.getCustDetails().get(i).setCustId(custId);
					session.save(customer.getCustDetails().get(i));
					if(i % 20 == 0){
						session.flush();
				        session.clear();
					}
				}
			}
			session.flush();
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateCustomer(CrmCustomer customer) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(customer);
			SQLQuery query = session.createSQLQuery("DELETE FROM tblcustomerdetails WHERE CustID = :cusId");
			query.setParameter("cusId", customer.getCustID());
			if(query.executeUpdate() > 0){
				for(int i = 0; i < customer.getCustDetails().size(); i++){
					customer.getCustDetails().get(i).setCustId(customer.getCustID());
					session.save(customer.getCustDetails().get(i));
					if(i % 20 == 0){
						session.flush();
				        session.clear();
					}
				}
			}
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(String custID) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM tblcustomer WHERE CustID = :custID");
			query.setParameter("custID", custID);
			if(query.executeUpdate() > 0){
				SQLQuery detailsQuery = session.createSQLQuery("DELETE FROM tblcustomerdetails WHERE CustID = :custId");
				detailsQuery.setParameter("custId", custID);
				session.getTransaction().commit();
				detailsQuery.executeUpdate();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCustomer> listCustomers() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmCustomer.class);
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}
	
	@SuppressWarnings("unchecked")
	private List<CrmCustomerDetails> listCustomerDetailsByCustId(String custId){
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(CrmCustomerDetails.class);
		criteria.add(Restrictions.eq("custId", custId));
		criteria.addOrder(Order.asc("aId"));
		criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
		return criteria.list();
	}

	@Override
	public CrmCustomer findCustomerById(String custID) {
		CrmCustomer customer = (CrmCustomer)transactionManager.getSessionFactory().getCurrentSession().get(CrmCustomer.class, custID);
		customer.setCustDetails(listCustomerDetailsByCustId(custID));
		return customer;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listCustomerIdAndName() {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCustomer.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("custID"),"custID").add(Projections.property("custName"),"custName"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PriceCode> listPriceCode() {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(PriceCode.class);
			criteria.addOrder(Order.asc("priceCode"));
			return (List<PriceCode>)criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.close();
		}
		return null;
	}

	@Override
	public CrmCustomer viewCustomerDetails(String custId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCustomer.class);
			criteria.add(Restrictions.eq("custID", custId));
			CrmCustomer customer = (CrmCustomer)criteria.uniqueResult();
			List<CrmContact> contacts = getContactRelatedToCustomer(custId);
			if(contacts != null){
				customer.setContacts(contacts);
			}
			
			List<CrmCase> cases = getCasesRelatedToCustomer(custId);
			if(cases != null){
				customer.setCases(cases);
			}
			
			List<CrmOpportunity> opportunities = getOpportunityRelatedToCustomer(custId);
			if(opportunities != null){
				customer.setOpportunities(opportunities);
			}
			return customer;
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<CrmContact> getContactRelatedToCustomer(String custId){
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmContact.class, "con").createAlias("con.customer", "cust");
			criteria.add(Restrictions.eq("cust.custID", custId));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("conID"), "conID")
					.add(Projections.property("conSalutation"), "conSalutation")
					.add(Projections.property("conFirstname"), "conFirstname")
					.add(Projections.property("conLastname"), "conLastname"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	private List<CrmCase> getCasesRelatedToCustomer(String custId){
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmCase.class, "case").createAlias("case.customer", "cust");
			criteria.add(Restrictions.eq("cust.custID", custId));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("caseId"), "caseId")
					.add(Projections.property("createDate"), "createDate")
					.add(Projections.property("subject"), "subject")
					.add(Projections.property("status"), "status"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	
	@SuppressWarnings("unchecked")
	private List<CrmOpportunity> getOpportunityRelatedToCustomer(String custId){
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(CrmOpportunity.class, "op").createAlias("op.customer", "cust");
			criteria.add(Restrictions.eq("cust.custID", custId));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("opId"), "opId")
					.add(Projections.property("opName"), "opName")
					.add(Projections.property("opAmount"), "opAmount")
					.add(Projections.property("opStageId"), "opStageId")
					.add(Projections.property("opCloseDate"), "opCloseDate"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return criteria.list();
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		return null;
	}

}
