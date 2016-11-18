package com.balancika.crm.dao.impl;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.model.AmeClass;
import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.CrmContact;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.CrmShipAddress;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.PriceCode;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DateTimeOperation;

@Repository
public class CrmCustomerDaoImpl extends CrmIdGenerator implements CrmCustomerDao{
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public boolean insertCustomer(CrmCustomer customer) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(customer.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String custId = ameIdAutoGenerator("CUST", customer.getMeDataSource());
			customer.setCustID(custId);
			customer.setCreateFrom("CRM");
			session.persist(customer);
			if(customer.getShipAddresses() != null){
				for(int i = 0; i < customer.getShipAddresses().size(); i++){
					customer.getShipAddresses().get(i).setModuleId("MT-CUS");
					customer.getShipAddresses().get(i).setDocId(custId);
					session.save(customer.getShipAddresses().get(i));
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
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateCustomer(CrmCustomer customer) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(customer.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			session.update(customer);
			SQLQuery query = session.createSQLQuery("DELETE FROM tblshipaddress WHERE docid = :custId");
			query.setParameter("custId", customer.getCustID());
			query.executeUpdate();
			for(int i = 0; i < customer.getShipAddresses().size(); i++){
				customer.getShipAddresses().get(i).setModuleId("MT-CUS");
				customer.getShipAddresses().get(i).setDocId(customer.getCustID());
				session.save(customer.getShipAddresses().get(i));
				if(i % 20 == 0){
					session.flush();
			        session.clear();
				}
			}
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteCustomer(CrmCustomer customer) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(customer.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM tblcustomer WHERE CustID = :custID");
			query.setParameter("custID", customer.getCustID());
			if(query.executeUpdate() > 0){
				SQLQuery detailsQuery = session.createSQLQuery("DELETE FROM tblshipaddress WHERE moduleid = :custId");
				detailsQuery.setParameter("custId", customer.getCustID());
				session.getTransaction().commit();
				detailsQuery.executeUpdate();
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}finally{
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCustomer> listCustomers(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCustomer.class);
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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
	private List<CrmShipAddress> listShipAdressesByCustId(String custId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmShipAddress.class);
			criteria.add(Restrictions.eq("docId", custId));
			criteria.addOrder(Order.asc("shipId"));
			criteria.setResultTransformer(Criteria.DISTINCT_ROOT_ENTITY);
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
	public CrmCustomer findCustomerById(String custID, MeDataSource dataSource) {
		setSessionFactory( new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			CrmCustomer customer =(CrmCustomer)session.get(CrmCustomer.class, custID);
			customer.setShipAddresses(listShipAdressesByCustId(custID, dataSource));
			return customer;
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
	public List<Object> listCustomerIdAndName(MeDataSource dataSource) {
		setSessionFactory( new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCustomer.class);
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("custID"),"custID")
					.add(Projections.property("custName"),"custName")
					.add(Projections.property("priceCode"), "priceCode"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<PriceCode> listPriceCode(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(PriceCode.class);
			criteria.addOrder(Order.asc("priceCode"));
			return (List<PriceCode>)criteria.list();
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
	@SuppressWarnings("unchecked")
	public List<AmeClass> listAmeClasses(MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(AmeClass.class);
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
	public CrmCustomer viewCustomerDetails(String custId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCustomer.class);
			criteria.add(Restrictions.eq("custID", custId));
			CrmCustomer customer = (CrmCustomer)criteria.uniqueResult();
			List<CrmContact> contacts = getContactRelatedToCustomer(custId, dataSource);
			if(contacts != null){
				customer.setContacts(contacts);
			}
			
			List<CrmCase> cases = getCasesRelatedToCustomer(custId, dataSource);
			if(cases != null){
				customer.setCases(cases);
			}
			
			List<CrmOpportunity> opportunities = getOpportunityRelatedToCustomer(custId, dataSource);
			if(opportunities != null){
				customer.setOpportunities(opportunities);
			}
			customer.setShipAddresses(listShipAdressesByCustId(custId,dataSource));
			return customer;
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
	private List<CrmContact> getContactRelatedToCustomer(String custId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session =  getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmContact.class, "con").createAlias("con.customer", "cust");
			criteria.add(Restrictions.eq("cust.custID", custId));
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("conID"), "conID")
					.add(Projections.property("conSalutation"), "conSalutation")
					.add(Projections.property("conFirstname"), "conFirstname")
					.add(Projections.property("conLastname"), "conLastname")
					.add(Projections.property("conPhone"),"conPhone")
					.add(Projections.property("conMobile"),"conMobile")
					.add(Projections.property("conEmial"),"conEmial")
					.add(Projections.property("conDepartment"),"conDepartment")
					.add(Projections.property("conTitle"),"conTitle"));
			criteria.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return criteria.list();
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
	private List<CrmCase> getCasesRelatedToCustomer(String custId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCase.class, "case").createAlias("case.customer", "cust");
			criteria.add(Restrictions.eq("cust.custID", custId));
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
	private List<CrmOpportunity> getOpportunityRelatedToCustomer(String custId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
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
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}

}
