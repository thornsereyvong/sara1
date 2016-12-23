package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.HBUItemDao;
import com.balancika.crm.model.HBUItem;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.HBUItemService;

@Service
@Transactional
public class HBUItemServiceImpl implements HBUItemService{
	
	@Autowired
	private HBUItemDao itemDao;

	@Override
	public boolean addCompetitorsToItem(HBUItem item) {
		return itemDao.addCompetitorsToItem(item);
	}

	@Override
	public boolean updateCompetitorOfItem(HBUItem item) {
		return itemDao.updateCompetitorOfItem(item);
	}

	@Override
	public HBUItem findHBUItemById(String itemId, MeDataSource dataSource) {
		return itemDao.findHBUItemById(itemId, dataSource);
	}

}
