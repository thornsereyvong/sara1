package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.HBUCompetitor;
import com.balancika.crm.model.MeDataSource;

public interface HBUCompetitorDao {

	boolean insertCompetitor(HBUCompetitor competitor);
	boolean updateCompetitor(HBUCompetitor competitor);
	boolean deleteCompetitor(HBUCompetitor competitor);
	HBUCompetitor findCompetitorById(String comId, MeDataSource dataSource);
	List<HBUCompetitor> listCompetitors(MeDataSource dataSource);
}
