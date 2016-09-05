package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.SaleOrder;

public interface SaleOrderService {

	boolean insertSaleOrder(SaleOrder saleOrder);
	boolean updateSaleOrder(SaleOrder saleOrder);
	boolean deleteSaleOrder(String saleId);
	SaleOrder findSaleOrderById(String saleId);
	List<Object> listSaleOrders();
	List<Object> listPrincipleOnStartup();
	String checkSaleOrderIdExist(String saleId);
}
