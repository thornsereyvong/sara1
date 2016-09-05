package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmEventLocation;

public interface CrmEventLocationDao {

	boolean insertEventLocation(CrmEventLocation location);
	boolean updateEventLocation(CrmEventLocation location);
	String deleteEventLocation(String id);
	List<CrmEventLocation> listEventLocations();
	CrmEventLocation findEventLocationById(String id);
}
