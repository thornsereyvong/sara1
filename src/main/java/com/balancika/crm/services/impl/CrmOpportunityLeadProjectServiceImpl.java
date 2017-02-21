package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityLeadProjectDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityLeadProjectService;

@Service
@Transactional
public class CrmOpportunityLeadProjectServiceImpl implements CrmOpportunityLeadProjectService{

	@Autowired
	private CrmOpportunityLeadProjectDao projectDao;
	
	@Override
	public boolean addOpportunityLeadProject(String opId, int lpId, MeDataSource dataSource) {
		return projectDao.addOpportunityLeadProject(opId, lpId, dataSource);
	}

	@Override
	public boolean deleteOpportunityLeadProject(String opId, int lpId, MeDataSource dataSource) {
		return projectDao.deleteOpportunityLeadProject(opId, lpId, dataSource);
	}

	@Override
	public List<Object> listOpportunityLeadProjectByOpId(String opId, MeDataSource dataSource) {
		return projectDao.listOpportunityLeadProjectByOpId(opId, dataSource);
	}

	@Override
	public Map<String, Object> startupOpportunityLeadProject(MeDataSource dataSource) {
		return projectDao.startupOpportunityLeadProject(dataSource);
	}

}
