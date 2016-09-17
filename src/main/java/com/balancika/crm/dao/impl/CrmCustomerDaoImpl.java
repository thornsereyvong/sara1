package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.CrmCustomerDetails;
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
			for(int i = 0; i < customer.getCustDetails().size(); i++){
				customer.getCustDetails().get(i).setCustId(custId);
				session.save(customer.getCustDetails().get(i));
				if(i % 20 == 0){
					session.flush();
			        session.clear();
				}
			}
			session.flush();
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
				if(detailsQuery.executeUpdate() > 0){
					return true;
				}
				session.getTransaction().commit();
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

}
