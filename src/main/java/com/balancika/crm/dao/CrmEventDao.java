package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmEvent;

public interface CrmEventDao {
	boolean insertEvent(CrmEvent event);
	boolean updateEvent(CrmEvent event);
	boolean deleteEnvent(String evId);
	List<CrmEvent> listEvents();
	Object findEventById(String evId);
	CrmEvent findEventDetailsById(String evId);
	List<CrmEvent> listEventsRelatedToLead(String leadId);
	List<CrmEvent> listEventsRelatedToOpportunity(String opId);
}
