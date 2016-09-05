package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.SaleOrder;

public interface SaleOrderDao {
	boolean insertSaleOrder(SaleOrder saleOrder);
	boolean updateSaleOrder(SaleOrder saleOrder);
	boolean deleteSaleOrder(String saleId);
	SaleOrder findSaleOrderById(String saleId);
	List<Object> listSaleOrders();
	List<Object> listPrincipleOnStartup();
	String checkSaleOrderIdExist(String saleId);
}
