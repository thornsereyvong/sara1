package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityDetailsDao;
import com.balancika.crm.model.CrmOpportunityDetails;
import com.balancika.crm.services.CrmOpportunityDetailsService;

@Service
@Transactional
public class CrmOpportunityDetailsServiceImpl implements CrmOpportunityDetailsService{

	@Autowired
	private CrmOpportunityDetailsDao opportunityDetailsDao;
	
	@Override
	public boolean insertOpportunityDetails(CrmOpportunityDetails details) {
		return opportunityDetailsDao.insertOpportunityDetails(details);
	}

	@Override
	public boolean updateOpportunityDetails(CrmOpportunityDetails details) {
		return opportunityDetailsDao.updateOpportunityDetails(details);
	}

	@Override
	public boolean deleteOpportunityDetails(int opDetailsId) {
		return opportunityDetailsDao.deleteOpportunityDetails(opDetailsId);
	}

	@Override
	public CrmOpportunityDetails findOpportunityDetailsById(int opDetailsId) {
		return opportunityDetailsDao.findOpportunityDetailsById(opDetailsId);
	}

	@Override
	public List<CrmOpportunityDetails> listOpportunityDetails() {
		return opportunityDetailsDao.listOpportunityDetails();
	}

	@Override
	public Map<String, Object> startUpPage() {
		return opportunityDetailsDao.startUpPage();
	}

}
