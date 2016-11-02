package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmEventLocation;
import com.balancika.crm.model.MeDataSource;

public interface CrmEventLocationDao {

	boolean insertEventLocation(CrmEventLocation location);
	boolean updateEventLocation(CrmEventLocation location);
	String deleteEventLocation(CrmEventLocation location);
	List<CrmEventLocation> listEventLocations(MeDataSource dataSource);
	CrmEventLocation findEventLocationById(String id, MeDataSource dataSource);
}
