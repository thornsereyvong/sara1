package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmEventDao;
import com.balancika.crm.model.CrmEvent;
import com.balancika.crm.services.CrmEventService;

@Service
@Transactional
public class CrmEventServiceImpl implements CrmEventService{

	@Autowired
	private CrmEventDao eventDao;
	
	@Override
	public boolean insertEvent(CrmEvent event) {
		return eventDao.insertEvent(event);	
	}

	@Override
	public boolean updateEvent(CrmEvent event) {
		return eventDao.updateEvent(event);
	}

	@Override
	public boolean deleteEnvent(String evId) {
		return eventDao.deleteEnvent(evId);
	}

	@Override
	public List<CrmEvent> listEvents() {
		return eventDao.listEvents();
	}

	@Override
	public Object findEventById(String evId) {
		return eventDao.findEventById(evId);
	}

	@Override
	public CrmEvent findEventDetailsById(String evId) {
		return eventDao.findEventDetailsById(evId);
	}

	@Override
	public List<CrmEvent> listEventsRelatedToLead(String leadId) {
		return eventDao.listEventsRelatedToLead(leadId);
	}

	@Override
	public List<CrmEvent> listEventsRelatedToOpportunity(String opId) {
		return eventDao.listEventsRelatedToOpportunity(opId);
	}

	@Override
	public List<CrmEvent> listEventsRelatedToModule(String moduleId) {
		return eventDao.listEventsRelatedToModule(moduleId);
	}
}
