package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityContactDao;
import com.balancika.crm.model.CrmOpportunityContact;
import com.balancika.crm.services.CrmOpportunityContactService;

@Service
@Transactional
public class CrmOpportunityContactServiceImpl implements CrmOpportunityContactService{

	@Autowired
	private CrmOpportunityContactDao opportunityContactDao;
	
	@Override
	public boolean insterOpportunityContact(CrmOpportunityContact opCon) {
		return opportunityContactDao.insterOpportunityContact(opCon);
	}

	@Override
	public boolean updateOpportunityContact(CrmOpportunityContact opCon) {
		return opportunityContactDao.updateOpportunityContact(opCon);
	}

	@Override
	public boolean deleteOpportunityContact(int opConId) {
		return opportunityContactDao.deleteOpportunityContact(opConId);
	}

	@Override
	public CrmOpportunityContact findOpportunityContactById(int opConId) {
		return opportunityContactDao.findOpportunityContactById(opConId);
	}

	@Override
	public Integer checkOpportunityContactIsExist(String opId, String conId) {
		return opportunityContactDao.checkOpportunityContactIsExist(opId, conId);
	}

	@Override
	public Object viewOpportunityContactById(int opConId) {
		return opportunityContactDao.viewOpportunityContactById(opConId);
	}

}
