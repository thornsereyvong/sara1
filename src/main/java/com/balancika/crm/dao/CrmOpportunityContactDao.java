package com.balancika.crm.dao;

import com.balancika.crm.model.CrmOpportunityContact;

public interface CrmOpportunityContactDao {
	boolean insterOpportunityContact(CrmOpportunityContact opCon);
	boolean updateOpportunityContact(CrmOpportunityContact opCon);
	boolean deleteOpportunityContact(int opConId);
	CrmOpportunityContact findOpportunityContactById(int opConId);
	Integer checkOpportunityContactIsExist(String opId, String conId);
	Object viewOpportunityContactById(int opConId);
}
