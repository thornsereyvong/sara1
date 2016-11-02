package com.balancika.crm.dao.impl;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

import com.balancika.crm.configuration.HibernateSessionFactory;
import com.balancika.crm.dao.SaleOrderDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.SaleOrder;
import com.balancika.crm.model.SaleOrderDetails;
import com.balancika.crm.utilities.CrmIdGenerator;

@Repository
public class SaleOrderDaoImpl extends CrmIdGenerator implements SaleOrderDao{
	
	@Override
	public boolean insertSaleOrder(SaleOrder saleOrder) {
		Session session = HibernateSessionFactory.getSessionFactory(saleOrder.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			String saleId = "";
			
			if(saleOrder.getSaleId().equals("") || saleOrder.getSaleId() == null){
				saleId = ameIdAutoGenerator("AR-SO");
				if(checkSaleOrderIdExist(saleId, saleOrder.getMeDataSource()).equals("EXIST")){
					saleId = ameIdAutoGenerator("AR-SO");
				}
			}else{
				saleId = saleOrder.getSaleId();
			}
			saleOrder.setSaleId(saleId);
			saleOrder.setPostStatus("Open");
			saleOrder.setPmtStatus("Unpaid");
			session.persist(saleOrder);
			if(insertSaleOrderDetails(saleOrder.getSaleOrderDetails(), saleId, saleOrder.getDisInvPer(),saleOrder.getMeDataSource()) == true){
				session.flush();
				session.getTransaction().commit();
				session.clear();
				session.close();
				return true;
			}else{
				session.getTransaction().rollback();
				session.clear();
				session.close();
				return false;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		}
		return false;
	}


	private boolean insertSaleOrderDetails(List<SaleOrderDetails> saleOrderDetails, String saleId, double disInvPer, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
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
			session.clear();
			session.close();
			return true;
		} catch (Exception e) {
			session.getTransaction().rollback();
			session.clear();
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
	
	private boolean deleteSaleOrderDetails(String saleId, MeDataSource dataSource){
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			session.beginTransaction();
			String sql = "DELETE FROM tblsaleorderdetails WHERE SalID = :saleId ;";
			SQLQuery query = session.createSQLQuery(sql);
			query.setParameter("saleId", saleId);
			session.getTransaction().commit();
			query.executeUpdate();
			this.deleteOpportunitySaleOrder(saleId, dataSource);
			session.close();
			return true;
		} catch (Exception e) {
			session.beginTransaction().rollback();
			session.clear();
			session.close();
			e.printStackTrace();
		}
		return false;
	}


	@Override
	public boolean updateSaleOrder(SaleOrder saleOrder) {
		Session session = HibernateSessionFactory.getSessionFactory(saleOrder.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			saleOrder.setPostStatus("Open");
			saleOrder.setPmtStatus("Unpaid");
			session.update(saleOrder);
			if(deleteSaleOrderDetails(saleOrder.getSaleId(), saleOrder.getMeDataSource()) == true){
				boolean status = insertSaleOrderDetails(saleOrder.getSaleOrderDetails(), saleOrder.getSaleId(), saleOrder.getDisInvPer(), saleOrder.getMeDataSource());
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
	public boolean deleteSaleOrder(SaleOrder saleOrder) {
		Session session = HibernateSessionFactory.getSessionFactory(saleOrder.getMeDataSource()).openSession();
		try {
			session.beginTransaction();
			if (deleteSaleOrderDetails(saleOrder.getSaleId(), saleOrder.getMeDataSource()) == true) {
				SQLQuery query = session.createSQLQuery("DELETE FROM tblsaleOrder WHERE SalID = :saleId ;");
				query.setParameter("saleId", saleOrder.getSaleId());
				session.getTransaction().commit();
				query.executeUpdate();
				this.deleteOpportunitySaleOrder(saleOrder.getSaleId(), saleOrder.getMeDataSource());
				session.close();
				return true;
			}
		} catch (Exception e) {
			e.getMessage();
			session.getTransaction().rollback();
			session.close();
		}
		return false;
	}

	
	@SuppressWarnings("unchecked")
	private List<SaleOrderDetails> listSaleOrderDetailsBySaleId(String saleId, MeDataSource dataSource){
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(SaleOrderDetails.class);
			criteria.add(Restrictions.eq("saleId", saleId));
			criteria.addOrder(Order.asc("lineNo"));
			return criteria.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}

	@Override
	public SaleOrder findSaleOrderById(String saleId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(SaleOrder.class);
			criteria.add(Restrictions.eq("saleId", saleId));
			SaleOrder saleOrder = (SaleOrder)criteria.uniqueResult();
			saleOrder.setSaleOrderDetails(listSaleOrderDetailsBySaleId(saleId, dataSource));
			return saleOrder;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<Object> listSaleOrders(MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listSaleOrders()");
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}


	@Override
	public List<Object> listPrincipleOnStartup(MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public String checkSaleOrderIdExist(String saleId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			Criteria criteria = session.createCriteria(SaleOrder.class);
			criteria.setProjection(Projections.projectionList().add(Projections.property("saleId")));
			criteria.add(Restrictions.eq("saleId", saleId));
			if(criteria.uniqueResult() != null){
				return "EXIST";
			}
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
		return "NOT_EXIST";
	}


	@SuppressWarnings("unchecked")
	@Override
	public List<SaleOrder> listSomeFieldsOfSaleOrder(String opId, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			SQLQuery query = session.createSQLQuery("CALL listCustomFieldsOfSaleorder(:opId)");
			query.setParameter("opId", opId);
			query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
			return query.list();
		} catch (Exception e) {
			e.getMessage();
		} finally {
			session.clear();
			session.close();
		}
		return null;
	}


	@Override
	public boolean updateSaleOrderPostStatus(String saleId, String status, MeDataSource dataSource) {
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("CALL updatePostStatus(:saleId, :postStatus)");
			query.setParameter("saleId", saleId);
			query.setParameter("postStatus", status);
			session.getTransaction().commit();
			if(query.executeUpdate() > 0){
				return true;
			}
		} catch (HibernateException e) {
			e.printStackTrace();
			session.getTransaction().rollback();
		} finally {
			session.clear();
			session.close();
		}
		return false;
	}
	
	private void deleteOpportunitySaleOrder(String saleId, MeDataSource dataSource){
		Session session = HibernateSessionFactory.getSessionFactory(dataSource).openSession();
		try {
			session.beginTransaction();
			SQLQuery query = session.createSQLQuery("DELETE FROM crm_opportunity_saleorder WHERE S_O_ID = :saleId ;");
			query.setParameter("saleId", saleId);
			query.executeUpdate();
			session.getTransaction().commit();
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally {
			session.clear();
			session.close();
		}
	}
	
}
