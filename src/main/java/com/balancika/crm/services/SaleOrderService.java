package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.SaleOrder;

public interface SaleOrderService {

	boolean insertSaleOrder(SaleOrder saleOrder);
	boolean updateSaleOrder(SaleOrder saleOrder);
	boolean deleteSaleOrder(SaleOrder saleOrder);
	SaleOrder findSaleOrderById(String saleId, MeDataSource dataSource);
	List<Object> listSaleOrders(MeDataSource dataSource);
	List<Object> listPrincipleOnStartup(MeDataSource dataSource);
	String checkSaleOrderIdExist(String saleId, MeDataSource dataSource);
	boolean updateSaleOrderPostStatus(String saleId, String status, MeDataSource dataSource);
}
