package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityDetailsDao;
import com.balancika.crm.model.CrmOpportunityDetails;
import com.balancika.crm.model.MeDataSource;
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
	public boolean deleteOpportunityDetails(CrmOpportunityDetails details) {
		return opportunityDetailsDao.deleteOpportunityDetails(details);
	}

	@Override
	public CrmOpportunityDetails findOpportunityDetailsById(int opDetailsId, MeDataSource dataSource) {
		return opportunityDetailsDao.findOpportunityDetailsById(opDetailsId, dataSource);
	}

	@Override
	public List<CrmOpportunityDetails> listOpportunityDetails(MeDataSource dataSource) {
		return opportunityDetailsDao.listOpportunityDetails(dataSource);
	}

	@Override
	public Map<String, Object> startUpPage(MeDataSource dataSource) {
		return opportunityDetailsDao.startUpPage(dataSource);
	}

	@Override
	public List<CrmOpportunityDetails> listOpportunityDetailsRelatedToOpportunity(String opId, MeDataSource dataSource) {
		return opportunityDetailsDao.listOpportunityDetailsRelatedToOpportunity(opId, dataSource);
	}

	@Override
	public boolean deleteOpportunityDetails(String opId, MeDataSource dataSource) {
		return opportunityDetailsDao.deleteOpportunityDetails(opId, dataSource);
	}

}
