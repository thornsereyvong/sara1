package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.HBUCompetitorDao;
import com.balancika.crm.model.HBUCompetitor;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.HBUCompetitorService;

@Service
@Transactional
public class HBUCompetitorServiceImpl implements HBUCompetitorService{

	@Autowired
	private HBUCompetitorDao competitorDao;
	
	@Override
	public boolean insertCompetitor(HBUCompetitor competitor) {
		return competitorDao.insertCompetitor(competitor);
	}

	@Override
	public boolean updateCompetitor(HBUCompetitor competitor) {
		return competitorDao.updateCompetitor(competitor);
	}

	@Override
	public boolean deleteCompetitor(HBUCompetitor competitor) {
		return competitorDao.deleteCompetitor(competitor);
	}

	@Override
	public HBUCompetitor findCompetitorById(String comId, MeDataSource dataSource) {
		return competitorDao.findCompetitorById(comId, dataSource);
	}

	@Override
	public List<HBUCompetitor> listCompetitors(MeDataSource dataSource) {
		return competitorDao.listCompetitors(dataSource);
	}

}
