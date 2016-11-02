package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityStageDao;
import com.balancika.crm.model.CrmOpportunityStage;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityStageService;

@Service
@Transactional
public class CrmOpportunityStageServiceImpl implements CrmOpportunityStageService{

	@Autowired
	private CrmOpportunityStageDao stageDao;
	
	@Override
	public boolean insertOpportunityStage(CrmOpportunityStage opStage) {
		return stageDao.insertOpportunityStage(opStage);
	}

	@Override
	public boolean updateOpportunityStage(CrmOpportunityStage opStage) {
		return stageDao.updateOpportunityStage(opStage);
	}

	@Override
	public String deleteOpportunityStage(CrmOpportunityStage opStage) {
		return stageDao.deleteOpportunityStage(opStage);
	}

	@Override
	public List<CrmOpportunityStage> listOpportunityStages(MeDataSource dataSource) {
		return stageDao.listOpportunityStages(dataSource);
	}

	@Override
	public CrmOpportunityStage findOpportunityStage(int opStageId, MeDataSource dataSource) {
		return stageDao.findOpportunityStage(opStageId, dataSource);
	}
}
