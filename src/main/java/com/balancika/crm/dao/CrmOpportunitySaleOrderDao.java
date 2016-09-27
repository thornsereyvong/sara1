package com.balancika.crm.dao;

import com.balancika.crm.model.CrmOpportunitySaleOrder;

public interface CrmOpportunitySaleOrderDao {

	boolean insertOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	boolean updateOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	boolean deleteOpportunitySaleOrder(int opSaleOrderId);
	CrmOpportunitySaleOrder findOpportunitySaleOrder(int opSaleOrderId);
	Integer checkOpportunitySaleOrderIsExist(String opId, String saleOrderId);
	Object viewOpportunitySaleOrder(int opSaleOrderId);
}
