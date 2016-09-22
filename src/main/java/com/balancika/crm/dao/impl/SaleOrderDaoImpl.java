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

import com.balancika.crm.dao.SaleOrderDao;
import com.balancika.crm.model.SaleOrder;
import com.balancika.crm.model.SaleOrderDetails;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class SaleOrderDaoImpl extends CrmIdGenerator implements SaleOrderDao{
	
	@Autowired
	private HibernateTransactionManager transactionManager;
	

	@Override
	public boolean insertSaleOrder(SaleOrder saleOrder) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String saleId = "";
			
			if(saleOrder.getSaleId().equals("") || saleOrder.getSaleId().equals(null)){
				saleId = ameIdAutoGenerator("AR-SO");
				if(checkSaleOrderIdExist(saleId).equals("EXIST")){
					saleId = ameIdAutoGenerator("AR-SO");
				}
			}else{
				saleId = saleOrder.getSaleId();
			}
			saleOrder.setSaleId(saleId);
			saleOrder.setPostStatus("Open");
			saleOrder.setPmtStatus("Unpaid");
			session.persist(saleOrder);
			if(insertSaleOrderDetails(saleOrder.getSaleOrderDetails(), saleId, saleOrder.getDisInvPer()) == true){
				session.flush();
				session.getTransaction().commit();
				session.close();
				return true;
			}else{
				session.getTransaction().rollback();
				session.close();
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;
	}


	private boolean insertSaleOrderDetails(List<SaleOrderDetails> saleOrderDetails, String saleId, double disInvPer) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			double disInv;
			for(int i = 0; i < saleOrderDetails.size() ; i++){
				disInv = generateDisInvByItem(saleOrderDetails.get(i).getNetTotalAmt(), disInvPer);
				saleOrderDetails.get(i).setSaleId(saleId);
				saleOrderDetails.get(i).setPostStatus("Open");
				saleOrderDetails.get(i).setItemStatus("CRM");
				saleOrderDetails.get(i).setDisInv(disInv);
				session.save(saleOrderDetails.get(i));
				if(i % 20 == 0){
					session.flush();
			        session.clear();
				}
			}
			session.getTransaction().commit();
			session.close();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.close();
			e.printStackTrace();
		}
		return false;
	}

	private double generateDisInvByItem(double netAmt, double persent){
		if(netAmt == 0 || persent == 0){
			return 0;
		}
		return (netAmt * persent / 100);
	}
	
	private boolean deleteSaleOrderDetails(String saleId){
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			String sql = "DELETE FROM tblsaleorderdetails WHERE SalID = :saleId ;";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("saleId", saleId);
			session.getTransaction().commit();
			if(query.executeUpdate() > 0){
				session.close();
				return true;
			}
		} catch (Exception e) {
			session.beginTransaction().rollback();
			session.close();
			e.getMessage();
		}
		return false;
	}


	@Override
	public boolean updateSaleOrder(SaleOrder saleOrder) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			saleOrder.setPostStatus("Open");
			saleOrder.setPmtStatus("Unpaid");
			session.update(saleOrder);
			if(deleteSaleOrderDetails(saleOrder.getSaleId()) == true){
				boolean status = insertSaleOrderDetails(saleOrder.getSaleOrderDetails(), saleOrder.getSaleId(), saleOrder.getDisInvPer());
				if(status == true){
					session.getTransaction().commit();
					session.close();
					return true;
				}
			}
		} catch (Exception e) {
			e.getMessage();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}


	@Override
	public boolean deleteSaleOrder(String saleId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			if (deleteSaleOrderDetails(saleId) == true) {
				SQLQuery query = session.createSQLQuery("DELETE FROM tblsaleOrder WHERE SalID = :saleId ;");
				query.setParameter("saleId", saleId);
				session.getTransaction().commit();
				if(query.executeUpdate() > 0){
					session.close();
					return true;
				}
			}
			
		} catch (Exception e) {
			e.getMessage();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	
	@SuppressWarnings("unchecked")
	private List<SaleOrderDetails> listSaleOrderDetailsBySaleId(String saleId){
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(SaleOrderDetails.class);
		criteria.add(Restrictions.eq("saleId", saleId));
		criteria.addOrder(Order.asc("lineNo"));
		//criteria.setResultTransformer(Transformers.aliasToBean(SaleOrderDetails.class));
		return criteria.list();
	}

	@Override
	public SaleOrder findSaleOrderById(String saleId) {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		Criteria criteria = session.createCriteria(SaleOrder.class);
		criteria.add(Restrictions.eq("saleId", saleId));
		SaleOrder saleOrder = (SaleOrder)criteria.uniqueResult();
		saleOrder.setSaleOrderDetails(listSaleOrderDetailsBySaleId(saleId));
		return saleOrder;
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listSaleOrders() {
		Session session = transactionManager.getSessionFactory().getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL listSaleOrders()");
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		return query.list();
	}


	@Override
	public List<Object> listPrincipleOnStartup() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String checkSaleOrderIdExist(String saleId) {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			Criteria criteria = session.createCriteria(SaleOrder.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("saleId")));
			criteria.add(Restrictions.eq("saleId", saleId));
			session.getTransaction().commit();
			if(criteria.uniqueResult() != null){
				return "EXIST";
			}
		} catch (Exception e) {
			e.getMessage();
			session.close();
		} 
		return "NOT_EXIST";
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SaleOrder> listSomeFieldsOfSaleOrder() {
		Session session = transactionManager.getSessionFactory().openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("CALL listCustomFieldsOfSaleorder()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.getMessage();
			session.close();
		} 
		return null;
	}
	
}
