package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.AmeItemDao;
import com.balancika.crm.model.AmeItem;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.AmeItemService;

@Service
@Transactional
public class AmeItemServiceImpl implements AmeItemService{

	@Autowired
	private AmeItemDao itemDao;
	
	@Override
	public List<AmeItem> listItems(MeDataSource dataSource) {
		return itemDao.listItems(dataSource);
	}

}
