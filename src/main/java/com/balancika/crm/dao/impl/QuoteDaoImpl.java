package com.balancika.crm.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;

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
import com.balancika.crm.dao.QuoteDao;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.Quote;
import com.balancika.crm.model.QuoteDetails;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class QuoteDaoImpl extends CrmIdGenerator implements QuoteDao{
	
	private SessionFactory sessionFactory;

	public final SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public final void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listQuoteStartupPage(MeDataSource dataSource) {	
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmCustomer.class, "cust");
			criteria.setProjection(Projections.projectionList()
					.add(Projections.property("custID"), "custID")
					.add(Projections.property("custName"), "custName")
					.add(Projections.property("aId"),"aId")
					.add(Projections.property("termCreditLimit"), "termCreditLimit")
					.add(Projections.property("termNetDueIn"), "termNetDueIn")
					.add(Projections.property("cust.priceCode"),"priceCode"));
			criteria.setResultTransformer(Transformers.aliasToBean(CrmCustomer.class));
			SQLQuery query = session.createSQLQuery("SELECT ClassID, Des AS ClassName FROM tblclass WHERE Inactive = 0;");
			SQLQuery emp = session.createSQLQuery("SELECT EmpID, EmpName FROM tblemployee;");
			SQLQuery item = session.createSQLQuery("SELECT ItemID,ItemName, COALESCE(i.UOMID,'') 'UOM' FROM tblitem i;");
			SQLQuery location = session.createSQLQuery("SELECT LocationID, Des AS LocationName FROM tbllocation;");
			SQLQuery uom = session.createSQLQuery("SELECT UomID,Des AS UomName FROM tbluom;");
			SQLQuery priceCode = session.createSQLQuery("SELECT PriceCode,Description FROM tblpricecode;");
			SQLQuery shipTo = session.createSQLQuery("SELECT moduleid 'moduleId', docid 'docId', shipid 'shipId', shipname 'shipName', inactive 'inactive' FROM tblshipaddress WHERE inactive = 0");
			SQLQuery empWithEmp = session.createSQLQuery("SELECT ue.EmpID from tbluseremployee ue WHERE ue.UID = '"+dataSource.getUserid()+"'");
			
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			emp.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			item.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			location.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			uom.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			priceCode.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			shipTo.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			empWithEmp.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			List<Object> arrMap = new ArrayList<Object>();
			Map<String, Object> map = new HashMap<String, Object>();
			List<CrmCustomer> customers = criteria.list();
			
			//System.out.println("-----------------------------"+listAllShipAddress(dataSource).size());
			
			map.put("customer", customers);
			map.put("shipToAddress", shipTo.list());
			map.put("classCode", query.list());
			map.put("employee", emp.list());
			map.put("item", item.list());
			map.put("location", location.list());
			map.put("uom", uom.list());
			map.put("priceCode", priceCode.list());
			map.put("empLinkUser", empWithEmp.uniqueResult());
			arrMap.add(map);
			return arrMap;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		
		return null;
	}
	
	/*@SuppressWarnings("unchecked")
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

	@SuppressWarnings("unchecked")
	private List<CrmShipAddress> listAllShipAddress(MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(CrmShipAddress.class);
			criteria.addOrder(Order.asc("docId"));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return null;
	}*/


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> findItemChange(String priceCode, String itemId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL spItemChange(:priceCode, :itemId)");
			query.setParameter("priceCode", priceCode);
			query.setParameter("itemId", itemId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return (Map<String, String>) query.uniqueResult();
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
	public Map<String, String> findQuantityAvailable(String itemId, String locationId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("SELECT Qty FROM tbluptodate WHERE ItemID = :itemId AND LocationID = :locationId ;");
			query.setParameter("itemId",itemId);
			query.setParameter("locationId", locationId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return (Map<String, String>)query.uniqueResult();
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
	public boolean insertQuote(Quote quote) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(quote.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String quoteId = "";
			if(quote.getSaleId().equalsIgnoreCase("") || quote.getSaleId() == null){
				quoteId = ameIdAutoGenerator("QUOTE", quote.getMeDataSource());
				if(checkQuoteIdExist(quoteId, quote.getMeDataSource()).equals("EXIST")){
					quoteId = ameIdAutoGenerator("QUOTE", quote.getMeDataSource());
				}
			}else{
				quoteId = quote.getSaleId();
			}
			quote.setSaleId(quoteId);
			quote.setPostStatus("Open");
			session.persist(quote);
			try {
				for(int i = 0; i < quote.getQuoteDetails().size(); i++){
					double disInv = generateDisInvByItem(quote.getQuoteDetails().get(i).getNetTotalAmt(), quote.getDisInvPer());
					quote.getQuoteDetails().get(i).setSaleId(quoteId);
					quote.getQuoteDetails().get(i).setPostStatus("Open");
					quote.getQuoteDetails().get(i).setItemStatus("CRM");
					quote.getQuoteDetails().get(i).setDisInv(disInv);
					session.save(quote.getQuoteDetails().get(i));
					if(i % 20 == 0){
						session.flush();
				        session.clear();
					} 
				}
			} catch (PersistenceException e) {
				session.getTransaction().rollback();
				session.close();
				sessionFactory.close();
				return false;
			}
			
			session.flush();
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean updateQuote(Quote quote) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(quote.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			quote.setPostStatus("Open");
			session.update(quote);
			session.getTransaction().commit();
			
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM tblsbquotedetails WHERE SalID = :saleId ;");
			query.setParameter("saleId", quote.getSaleId());
			if(query.executeUpdate() > 0){
				for(int i = 0; i < quote.getQuoteDetails().size(); i++){
					double disInv = generateDisInvByItem(quote.getQuoteDetails().get(i).getNetTotalAmt(), quote.getDisInvPer());
					quote.getQuoteDetails().get(i).setSaleId(quote.getSaleId());
					quote.getQuoteDetails().get(i).setPostStatus("Open");
					quote.getQuoteDetails().get(i).setItemStatus("CRM");
					quote.getQuoteDetails().get(i).setDisInv(disInv);
					session.save(quote.getQuoteDetails().get(i));
					if(i % 20 == 0){
						session.flush();
				        session.clear();
					}
				}
			}
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public boolean deleteQuote(Quote quote) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(quote.getMeDataSource()));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM tblsbquotedetails WHERE SalID = :quoteId ;");
			query.setParameter("quoteId", quote.getSaleId());
			query.executeUpdate();
			session.delete(quote);
			this.deleteOpportunityQuote(quote.getSaleId(), quote.getMeDataSource());
			session.getTransaction().commit();
			session.clear();
			session.close();
			sessionFactory.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		return false;
	}

	@Override
	public Quote findQuoteById(String quoteId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Quote.class);
			criteria.add(Restrictions.eq("saleId", quoteId));
			Quote quote = (Quote)criteria.uniqueResult();
			quote.setQuoteDetails(lisQuoteDetails(quoteId, dataSource));
			session.clear();
			session.close();
			sessionFactory.close();
			return quote;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.clear();
			session.close();
			sessionFactory.close();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<QuoteDetails> lisQuoteDetails(String quoteId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("SELECT SalID AS saleId, LineNo AS lineNo, ItemID AS itemId,"
					+ "UomID AS uomId, LocationID AS locationId, SalQty AS saleQuantity, UnitPrice AS unitPrice, TotalAmt AS totalAmt, "
					+ "PostStatus AS postStatus, NetTotalAmt AS netTotalAmt, DisDol AS disDol, DisPer AS disPer, STaxDol AS staxDol, "
					+ "STaxPer AS staxPer, VTaxDol AS vtaxDol, VTaxPer, vtaxPer, ItemStatus AS itemStatus, ReportPrice AS reportPrice,"
					+ "Factor AS factor, IsVariable AS isVariable, ClassID AS classId, DisInv AS disInv "
					+ "FROM tblsbquotedetails WHERE SalID = :saleId  ORDER BY LineNo ASC ;");
			query.setParameter("saleId", quoteId);
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
	
	public double generateDisInvByItem(double netAmt, double persent){
		if(netAmt == 0 || persent == 0){
			return 0;
		}
		return (netAmt * persent / 100);
	}

	@Override
	public String checkQuoteIdExist(String quoteId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			Criteria criteria = session.createCriteria(Quote.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("saleId"), "quoteId"));
			criteria.add(Restrictions.eq("saleId", quoteId));
			if(criteria.uniqueResult() != null){
				return "EXIST";
			}
			return "NOT_EXIST";
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
	public List<Quote> listQuotes(MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listQuotes()");
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
	public String convertQuoteToSaleOrder(MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quote> listCustomFieldOfQuotes(String opId, MeDataSource dataSource) {
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCustomFieldsOfQuotations(:opId)");
			query.setParameter("opId", opId);
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
	
	
	private void deleteOpportunityQuote(String quoteId, MeDataSource dataSource){
		setSessionFactory(new HibernateSessionFactory().getSessionFactory(dataSource));
		Session session = getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_opportunity_quote WHERE QuoteID = :quoteId ;");
			query.setParameter("quoteId", quoteId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
			
		} finally {
			session.clear();
			session.close();
			sessionFactory.close();
		}
	}
}