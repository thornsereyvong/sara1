package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmEvent;
import com.balancika.crm.model.MeDataSource;

public interface CrmEventService {
	boolean insertEvent(CrmEvent event);
	boolean updateEvent(CrmEvent event);
	boolean deleteEnvent(CrmEvent event);
	List<CrmEvent> listEvents(MeDataSource dataSource);
	Object findEventById(String evId, MeDataSource dataSource);
	CrmEvent findEventDetailsById(String evId, MeDataSource dataSource);
	List<CrmEvent> listEventsRelatedToLead(String leadId, MeDataSource dataSource);
	List<CrmEvent> listEventsRelatedToOpportunity(String opId, MeDataSource dataSource);
	List<CrmEvent> listEventsRelatedToModule(String moduleId, MeDataSource dataSource);
}
