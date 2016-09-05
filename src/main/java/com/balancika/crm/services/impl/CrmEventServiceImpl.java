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
		try {
			return eventDao.insertEvent(event);
		} catch (Exception e) {
			return false;
		}	
	}

	@Override
	public boolean updateEvent(CrmEvent event) {
		try {
			return eventDao.updateEvent(event);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteEnvent(String evId) {
		try {
			return eventDao.deleteEnvent(evId);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<CrmEvent> listEvents() {
		try {
			return eventDao.listEvents();
		} catch (Exception e) {
			return null;
		}
		
	}

	@Override
	public Object findEventById(String evId) {
		try {
			return eventDao.findEventById(evId);
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public CrmEvent findEventDetailsById(String evId) {
		try {
			return eventDao.findEventDetailsById(evId);
		} catch (Exception e) {
			return null;
		}
	}
}
