package com.balancika.crm.dao;

import com.balancika.crm.model.HBUItem;
import com.balancika.crm.model.MeDataSource;

public interface HBUItemDao {
	boolean addCompetitorsToItem(HBUItem item);
	boolean updateCompetitorOfItem(HBUItem item);
	HBUItem findHBUItemById(String itemId,MeDataSource dataSource);
}
