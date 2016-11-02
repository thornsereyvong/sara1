package com.balancika.crm.dao;

import com.balancika.crm.model.CrmOpportunitySaleOrder;
import com.balancika.crm.model.MeDataSource;

public interface CrmOpportunitySaleOrderDao {

	boolean insertOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	boolean updateOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	boolean deleteOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder);
	CrmOpportunitySaleOrder findOpportunitySaleOrder(int opSaleOrderId, MeDataSource dataSource);
	Integer checkOpportunitySaleOrderIsExist(String opId, String saleOrderId, MeDataSource dataSource);
	Object viewOpportunitySaleOrder(int opSaleOrderId, MeDataSource dataSource);
}
