package com.balancika.crm.dao.impl;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.model.AmeClass;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.CrmShipAddress;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.PriceCode;
import com.balancika.crm.utilities.AppUtilities;
import com.balancika.crm.utilities.CrmIdGenerator;
import com.balancika.crm.utilities.DBConnection;

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
			if(customer.getShipAddresses() != null){
				for(int i = 0; i < customer.getShipAddresses().size(); i++){
					customer.getShipAddresses().get(i).setModuleId("MT-CUS");
					customer.getShipAddresses().get(i).setDocId(customer.getCustID());
					session.save(customer.getShipAddresses().get(i));
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
			SQLQuery query = session.createSQLQuery("CALL crmDeleteModuleRelatedToCustomer(:custId)");
			query.setParameter("custId", customer.getCustID());
			query.executeUpdate();
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

	@SuppressWarnings("unchecked")
	@Override
	public List<CrmCustomer> listCustomers(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCustomer.class);
			criteria.addOrder(Order.desc("custID"));
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
			criteria.add(Restrictions.eq("approval", 1));
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
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Object> creditInfo(String custId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL spLoad_Credit_Limit(:custId,'',now())");
			query.setParameter("custId", custId);
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
	public Map<String, Object> creditInfoByCustomer(String custId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			
			System.out.println(custId+"--------------------------");
			
			Map<String, Object> map = new HashMap<>();
			SQLQuery query1 = session.createSQLQuery("CALL crm_customer_creditLimit_1(:custId,'')");
			query1.setParameter("custId", custId);
			query1.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			map.put("CREDIT_LIMIT", query1.list());
			
			SQLQuery query2 = session.createSQLQuery("CALL crm_customer_creditLimit_2(:custId,'')");
			query2.setParameter("custId", custId);
			query2.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			map.put("OUT_STAND", query2.list());
			
			SQLQuery query3 = session.createSQLQuery("CALL crm_customer_creditLimit_3(:custId,'')");
			query3.setParameter("custId", custId);
			query3.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			map.put("TEMP_CREDIT", query3.list());
			
			SQLQuery query4 = session.createSQLQuery("CALL crm_customer_creditLimit_4(:custId,'')");
			query4.setParameter("custId", custId);
			query4.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			map.put("INVOICE", query4.list());
			
			return map;
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
	public Map<String, Object> viewCustomerById(String custId, String userId, MeDataSource dataSource) {
		Map<String, Object> map = new HashMap<String, Object>();
		try(Connection con = DBConnection.getConnection(dataSource)){
			CallableStatement cs= con.prepareCall("{call crmViewCustomerById(?,?)}");
			cs.setString(1, custId);
			cs.setString(2, userId);
			boolean isResultSet = cs.execute();
			int rsCount = 0;
			String[] key = {"TASKS","TASK_STATUS","EVENTS","EVENT_LOCATION","CALLS","CALL_STATUS","MEETINGS","MEETING_STATUS","CONTACTS","NOTES","ASSIGN_TO","TAG_TO","INDUSTRY","GROUP","PRICE_CODE","TYPE","QUOTES","SALE_ORDERS","OPPORTUNITIES","CASES","CONTACT_RELATED_TO"}; // 
			while(isResultSet){
				ResultSet rs = cs.getResultSet();
				map.put(key[rsCount], AppUtilities.aliasToMaps(rs));
				rs.close();
				isResultSet = cs.getMoreResults();
				rsCount++;
			}
			map.put("CUSTOMER", findCustomerById(custId, dataSource));
			return map;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
