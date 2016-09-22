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
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.QuoteDao;
import com.balancika.crm.model.Quote;
import com.balancika.crm.model.QuoteDetails;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class QuoteDaoImpl extends CrmIdGenerator implements QuoteDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public List<Object> listQuoteStartupPage() {	
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery cust = session.createSQLQuery("SELECT C.CustID,C.CustName, C.AID AS AIDMaster,COALESCE(C.TermNetDueIn,0) 'TermNetDueIn',"
												+ "COALESCE(CD.AID,'') 'AID',COALESCE(CD.Address,'') 'Address',COALESCE(C.PriceCode) 'PriceCode' "
												+ "FROM tblcustomer C LEFT JOIN tblcustomerdetails CD on C.CustID = CD.CustID;");
		SQLQuery query = session.createSQLQuery("SELECT ClassID, Des AS ClassName FROM tblclass WHERE Inactive = 0;");
		SQLQuery emp = session.createSQLQuery("SELECT EmpID, EmpName FROM tblemployee;");
		SQLQuery item = session.createSQLQuery("SELECT ItemID,ItemName, COALESCE(i.UOMID,'') 'UOM' FROM tblitem i;");
		SQLQuery location = session.createSQLQuery("SELECT LocationID, Des AS LocationName FROM tbllocation;");
		SQLQuery uom = session.createSQLQuery("SELECT UomID,Des AS UomName FROM tbluom;");
		SQLQuery priceCode = session.createSQLQuery("SELECT PriceCode,Description FROM tblpricecode;");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		cust.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		emp.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		item.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		location.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		uom.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		priceCode.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Object> arrMap = new ArrayList<Object>();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customer", cust.list());
		map.put("classCode", query.list());
		map.put("employee", emp.list());
		map.put("item", item.list());
		map.put("location", location.list());
		map.put("uom", uom.list());
		map.put("priceCode", priceCode.list());
		arrMap.add(map);
		return arrMap;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> findItemChange(String priceCode, String itemId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		
		SQLQuery query = session.createSQLQuery("CALL spItemChange(:priceCode, :itemId)");
		query.setParameter("priceCode", priceCode);
		query.setParameter("itemId", itemId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return (Map<String, String>) query.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> findQuantityAvailable(String itemId, String locationId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("SELECT Qty FROM tbluptodate WHERE ItemID = :itemId AND LocationID = :locationId ;");
		query.setParameter("itemId",itemId);
		query.setParameter("locationId", locationId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return (Map<String, String>)query.uniqueResult();
	}

	@Override
	public boolean insertQuote(Quote quote) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String quoteId = "";
			if(quote.getSaleId().equalsIgnoreCase("")){
				quoteId = ameIdAutoGenerator("QUOTE");
			}else{
				quoteId = quote.getSaleId();
			}
			quote.setSaleId(quoteId);
			quote.setPmtStatus("Unpaid");
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
				return false;
			}
			
			session.flush();
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@Override
	public boolean updateQuote(Quote quote) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			quote.setPmtStatus("Unpaid");
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
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@Override
	public boolean deleteQuote(String quoteId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			Quote quote = new Quote();
			quote.setSaleId(quoteId);
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM tblsbquotedetails WHERE SalID = :quoteId ;");
			query.setParameter("quoteId", quoteId);
			if(query.executeUpdate() > 0){
				session.delete(quote);
			}
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	@Override
	public Quote findQuoteById(String quoteId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Quote.class);
			criteria.add(Restrictions.eq("saleId", quoteId));
			Quote quote = (Quote)criteria.uniqueResult();
			quote.setQuoteDetails(lisQuoteDetails(quoteId));
			session.close();
			return quote;
		} catch (Exception e) {
			e.getMessage();
			session.close();
		}
		
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public List<QuoteDetails> lisQuoteDetails(String quoteId){
		Session session = transactionManager.getSessionFactory().openSession();
		SQLQuery query = session.createSQLQuery("SELECT SalID AS saleId, LineNo AS lineNo, ItemID AS itemId,"
				+ "UomID AS uomId, LocationID AS locationId, SalQty AS saleQuantity, UnitPrice AS unitPrice, TotalAmt AS totalAmt, "
				+ "PostStatus AS postStatus, NetTotalAmt AS netTotalAmt, DisDol AS disDol, DisPer AS disPer, STaxDol AS staxDol, "
				+ "STaxPer AS staxPer, VTaxDol AS vtaxDol, VTaxPer, vtaxPer, ItemStatus AS itemStatus, ReportPrice AS reportPrice,"
				+ "Factor AS factor, IsVariable AS isVariable, ClassID AS classId, DisInv AS disInv "
				+ "FROM tblsbquotedetails WHERE SalID = :saleId  ORDER BY LineNo ASC ;");
		query.setParameter("saleId", quoteId);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
	
	public double generateDisInvByItem(double netAmt, double persent){
		if(netAmt == 0 || persent == 0){
			return 0;
		}
		return (netAmt * persent / 100);
	}

	@Override
	public String checkQuoteIdExist(String quoteId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(Quote.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("saleId"), "quoteId"));
			criteria.add(Restrictions.eq("saleId", quoteId));
			session.getTransaction().commit();
			if(criteria.uniqueResult() != null){
				return "EXIST";
			}
			
		} catch (HibernateException e) {
			e.getMessage();
		}
		return "NOT_EXIST";
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quote> listQuotes() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listQuotes()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}

	@Override
	public String convertQuoteToSaleOrder() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Quote> listCustomFieldOfQuotes() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listCustomFieldsOfQuotations()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}
}