package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmEventLocation;

public interface CrmEventLocationService {
	boolean insertEventLocation(CrmEventLocation location);
	boolean updateEventLocation(CrmEventLocation location);
	String deleteEventLocation(String id);
	List<CrmEventLocation> listEventLocations();
	CrmEventLocation findEventLocationById(String id);
}
