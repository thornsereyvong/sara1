package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmEventLocationDao;
import com.balancika.crm.model.CrmEventLocation;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmEventLocationService;

@Service
@Transactional
public class CrmEventLocationServiceImpl implements CrmEventLocationService{

	@Autowired
	private CrmEventLocationDao locationDao;
		
	@Override
	public boolean insertEventLocation(CrmEventLocation location) {
		return locationDao.insertEventLocation(location);
	}

	@Override
	public boolean updateEventLocation(CrmEventLocation location) {
		return locationDao.updateEventLocation(location);
	}

	@Override
	public String deleteEventLocation(CrmEventLocation location) {
		return locationDao.deleteEventLocation(location);
	}

	@Override
	public List<CrmEventLocation> listEventLocations(MeDataSource dataSource) {
		return locationDao.listEventLocations(dataSource);
	}

	@Override
	public CrmEventLocation findEventLocationById(String id, MeDataSource dataSource) {
		return locationDao.findEventLocationById(id, dataSource);
	}
}
