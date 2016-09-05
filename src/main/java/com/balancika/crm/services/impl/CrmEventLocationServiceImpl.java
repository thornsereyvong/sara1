package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmEventLocationDao;
import com.balancika.crm.model.CrmEventLocation;
import com.balancika.crm.services.CrmEventLocationService;

@Service
@Transactional
public class CrmEventLocationServiceImpl implements CrmEventLocationService{

	@Autowired
	private CrmEventLocationDao locationDao;
		
	@Override
	public boolean insertEventLocation(CrmEventLocation location) {
		try{
			return locationDao.insertEventLocation(location);
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean updateEventLocation(CrmEventLocation location) {
		try{
			return locationDao.updateEventLocation(location);
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public String deleteEventLocation(String id) {
		return locationDao.deleteEventLocation(id);
	}

	@Override
	public List<CrmEventLocation> listEventLocations() {
		try{
			return locationDao.listEventLocations();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public CrmEventLocation findEventLocationById(String id) {
		try{
			return locationDao.findEventLocationById(id);
		}catch(Exception e){
			return null;
		}
	}
}
