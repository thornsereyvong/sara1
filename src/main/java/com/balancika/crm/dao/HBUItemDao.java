package com.balancika.crm.dao;

import com.balancika.crm.model.HBUItem;
import com.balancika.crm.model.HBUItemCompetitor;
import com.balancika.crm.model.HBUItemCustomer;
import com.balancika.crm.model.MeDataSource;

public interface HBUItemDao {
	boolean addCompetitorsToItem(HBUItemCompetitor itemCompetitor);
	boolean updateCompetitorOfItem(HBUItem item);
	boolean addCustomerOfItem(HBUItemCustomer itemCustomer);
	HBUItem findHBUItemById(String itemId,MeDataSource dataSource);
}
