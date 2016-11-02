package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.SaleOrder;

public interface SaleOrderDao {
	boolean insertSaleOrder(SaleOrder saleOrder);
	boolean updateSaleOrder(SaleOrder saleOrder);
	boolean deleteSaleOrder(SaleOrder saleOrder);
	SaleOrder findSaleOrderById(String saleId, MeDataSource dataSource);
	List<Object> listSaleOrders(MeDataSource dataSource);
	List<Object> listPrincipleOnStartup(MeDataSource dataSource);
	String checkSaleOrderIdExist(String saleId, MeDataSource dataSource);
	List<SaleOrder> listSomeFieldsOfSaleOrder(String opId, MeDataSource dataSource);
	boolean updateSaleOrderPostStatus(String saleId, String status, MeDataSource dataSource);
}
