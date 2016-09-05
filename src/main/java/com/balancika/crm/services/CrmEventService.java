package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmEvent;

public interface CrmEventService {
	boolean insertEvent(CrmEvent event);
	boolean updateEvent(CrmEvent event);
	boolean deleteEnvent(String evId);
	List<CrmEvent> listEvents();
	Object findEventById(String evId);
	CrmEvent findEventDetailsById(String evId);
}
