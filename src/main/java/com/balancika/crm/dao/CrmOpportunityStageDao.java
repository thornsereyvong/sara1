package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmOpportunityStage;

public interface CrmOpportunityStageDao {

	boolean insertOpportunityStage(CrmOpportunityStage opStage);
	boolean updateOpportunityStage(CrmOpportunityStage opStage);
	String deleteOpportunityStage(int opStageId);
	List<CrmOpportunityStage> listOpportunityStages();
	CrmOpportunityStage findOpportunityStage(int opStageId);
}
