package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.model.CrmCustomer;
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
			customer.setCustID(ameIdAutoGenerator("CUST"));
			session.save(customer);
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
				session.getTransaction().commit();
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

	@Override
	public CrmCustomer findCustomerById(String custID) {
		return (CrmCustomer)transactionManager.getSessionFactory().getCurrentSession().get(CrmCustomer.class, custID);
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
