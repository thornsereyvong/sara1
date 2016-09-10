package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmEvent;

public interface CrmEventService {
	boolean insertEvent(String eventJson);
	boolean updateEvent(String eventJson);
	boolean deleteEnvent(String evId);
	List<CrmEvent> listEvents();
	Object findEventById(String evId);
	CrmEvent findEventDetailsById(String evId);
	List<CrmEvent> listEventsRelatedToLead(String leadId);
	List<CrmEvent> listEventsRelatedToOpportunity(String opId);
}
