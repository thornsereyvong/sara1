package com.balancika.crm.services;

import com.balancika.crm.model.CrmOpportunityContact;

public interface CrmOpportunityContactService {
	boolean insterOpportunityContact(CrmOpportunityContact opCon);
	boolean updateOpportunityContact(CrmOpportunityContact opCon);
	boolean deleteOpportunityContact(int opConId);
	CrmOpportunityContact findOpportunityContactById(int opConId);
}
