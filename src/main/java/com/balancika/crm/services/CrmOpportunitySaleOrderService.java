package com.balancika.crm.services;

import com.balancika.crm.model.CrmOpportunitySaleOrder;

public interface CrmOpportunitySaleOrderService {
	boolean insertOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	boolean updateOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	boolean deleteOpportunitySaleOrder(int opSaleOrderId);
	CrmOpportunitySaleOrder findOpportunitySaleOrder(int opSaleOrderId);
	Integer checkOpportunitySaleOrderIsExist(String opId, String saleOrderId);
	Object viewOpportunitySaleOrder(int opSaleOrderId);
}
