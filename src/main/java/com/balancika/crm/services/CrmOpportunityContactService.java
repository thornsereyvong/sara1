package com.balancika.crm.services;

import com.balancika.crm.model.CrmOpportunityContact;
import com.balancika.crm.model.MeDataSource;

public interface CrmOpportunityContactService {
	boolean insterOpportunityContact(CrmOpportunityContact opCon);
	boolean updateOpportunityContact(CrmOpportunityContact opCon);
	boolean deleteOpportunityContact(CrmOpportunityContact opCon);
	CrmOpportunityContact findOpportunityContactById(int opConId, MeDataSource dataSource);
	Integer checkOpportunityContactIsExist(String opId, String conId, MeDataSource dataSource);
	Object viewOpportunityContactById(int opConId, MeDataSource dataSource);
}
