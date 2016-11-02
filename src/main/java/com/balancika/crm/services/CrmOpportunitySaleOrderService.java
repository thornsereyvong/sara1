package com.balancika.crm.services;

import com.balancika.crm.model.CrmOpportunitySaleOrder;
import com.balancika.crm.model.MeDataSource;

public interface CrmOpportunitySaleOrderService {
	boolean insertOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	boolean updateOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	boolean deleteOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	CrmOpportunitySaleOrder findOpportunitySaleOrder(int opSaleOrderId, MeDataSource dataSource);
	Integer checkOpportunitySaleOrderIsExist(String opId, String saleOrderId, MeDataSource dataSource);
	Object viewOpportunitySaleOrder(int opSaleOrderId, MeDataSource dataSource);
}
