package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.AmeItem;
import com.balancika.crm.model.MeDataSource;

public interface AmeItemService{
	
	public List<AmeItem> listItems(MeDataSource dataSource);
	
}
