package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmOpportunityStage;
import com.balancika.crm.model.MeDataSource;

public interface CrmOpportunityStageService {

	boolean insertOpportunityStage(CrmOpportunityStage opStage);
	boolean updateOpportunityStage(CrmOpportunityStage opStage);
	String deleteOpportunityStage(CrmOpportunityStage opStage);
	List<CrmOpportunityStage> listOpportunityStages(MeDataSource dataSource);
	CrmOpportunityStage findOpportunityStage(int opStageId, MeDataSource dataSource);
}
