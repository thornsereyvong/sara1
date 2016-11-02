package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmEventDao;
import com.balancika.crm.model.CrmEvent;
import com.balancika.crm.model.MeDataSource;
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
	public boolean deleteEnvent(CrmEvent event) {
		return eventDao.deleteEnvent(event);
	}

	@Override
	public List<CrmEvent> listEvents(MeDataSource dataSource) {
		return eventDao.listEvents(dataSource);
	}

	@Override
	public Object findEventById(String evId,MeDataSource dataSource) {
		return eventDao.findEventById(evId, dataSource);
	}

	@Override
	public CrmEvent findEventDetailsById(String evId, MeDataSource dataSource) {
		return eventDao.findEventDetailsById(evId, dataSource);
	}

	@Override
	public List<CrmEvent> listEventsRelatedToLead(String leadId, MeDataSource dataSource) {
		return eventDao.listEventsRelatedToLead(leadId, dataSource);
	}

	@Override
	public List<CrmEvent> listEventsRelatedToOpportunity(String opId, MeDataSource dataSource) {
		return eventDao.listEventsRelatedToOpportunity(opId, dataSource);
	}

	@Override
	public List<CrmEvent> listEventsRelatedToModule(String moduleId, MeDataSource dataSource) {
		return eventDao.listEventsRelatedToModule(moduleId, dataSource);
	}
}
