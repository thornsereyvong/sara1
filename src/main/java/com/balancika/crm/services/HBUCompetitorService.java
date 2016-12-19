package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.dao.HBUCompetitorDao;
import com.balancika.crm.model.HBUCompetitor;
import com.balancika.crm.model.MeDataSource;

public interface HBUCompetitorService extends HBUCompetitorDao{
	
	@Override
	public boolean insertCompetitor(HBUCompetitor competitor);
	
	@Override
	public boolean updateCompetitor(HBUCompetitor competitor);
	
	@Override
	public boolean deleteCompetitor(HBUCompetitor competitor);
	
	@Override
	public HBUCompetitor findCompetitorById(String comId, MeDataSource dataSource);
	
	@Override
	public List<HBUCompetitor> listCompetitors(MeDataSource dataSource);
}
