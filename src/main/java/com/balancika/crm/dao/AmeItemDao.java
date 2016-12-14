package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.AmeItem;
import com.balancika.crm.model.MeDataSource;

public interface AmeItemDao {
	List<AmeItem> listItems(MeDataSource dataSource);
}
