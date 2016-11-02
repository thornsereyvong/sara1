package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityContactDao;
import com.balancika.crm.model.CrmOpportunityContact;
import com.balancika.crm.model.MeDataSource;
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
	public boolean deleteOpportunityContact(CrmOpportunityContact opCon) {
		return opportunityContactDao.deleteOpportunityContact(opCon);
	}

	@Override
	public CrmOpportunityContact findOpportunityContactById(int opConId, MeDataSource dataSource) {
		return opportunityContactDao.findOpportunityContactById(opConId, dataSource);
	}

	@Override
	public Integer checkOpportunityContactIsExist(String opId, String conId, MeDataSource dataSource) {
		return opportunityContactDao.checkOpportunityContactIsExist(opId, conId, dataSource);
	}

	@Override
	public Object viewOpportunityContactById(int opConId, MeDataSource dataSource) {
		return opportunityContactDao.viewOpportunityContactById(opConId, dataSource);
	}

}
