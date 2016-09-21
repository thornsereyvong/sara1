package com.balancika.crm.dao;

import com.balancika.crm.model.CrmOpportunityContact;

public interface CrmOpportunityContactDao {
	boolean insterOpportunityContact(CrmOpportunityContact opCon);
	boolean updateOpportunityContact(CrmOpportunityContact opCon);
	boolean deleteOpportunityContact(int opConId);
}
