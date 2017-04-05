package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityDao;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmOpportunityService;


@Service
@Transactional
public class CrmOpportunityServiceImpl implements CrmOpportunityService{

	@Autowired
	private CrmOpportunityDao opDao;
	
	@Override
	public boolean isInsertOpportunity(CrmOpportunity opportunity) {
		return opDao.isInsertOpportunity(opportunity);
	}

	@Override
	public boolean isUpdateOpportunity(CrmOpportunity opportunity) {
		return opDao.isUpdateOpportunity(opportunity);
	}

	@Override
	public boolean isDeleteOpportunity(CrmOpportunity opportunity) {
		return opDao.isDeleteOpportunity(opportunity);
	}

	@Override
	public Object findOpportunityById(String opId, MeDataSource dataSource) {
		return opDao.findOpportunityById(opId, dataSource);
	}

	@Override
	public List<CrmOpportunity> listOpportunities(MeDataSource dataSource) {
		return opDao.listOpportunities(dataSource);
	}

	@Override
	public CrmOpportunity findOpportunityDetailsById(String opId, MeDataSource dataSource) {
		return opDao.findOpportunityDetailsById(opId, dataSource);
	}

	@Override
	public List<Object> listOpportunitiesWithSpecificUser(String username, MeDataSource dataSource) {
		return opDao.listOpportunitiesWithSpecificUser(username, dataSource);
	}

	@Override
	public List<Object> listContactsRelatedToOpportuntiy(String opId, MeDataSource dataSource) {
		return opDao.listContactsRelatedToOpportuntiy(opId, dataSource);
	}

	@Override
	public List<Object> listQuotationsRelatedToOpportuntiy(String opId, MeDataSource dataSource) {
		return opDao.listQuotationsRelatedToOpportuntiy(opId, dataSource);
	}

	@Override
	public List<Object> listSaleOrdersRelatedToOpportuntiy(String opId, MeDataSource dataSource) {
		return opDao.listSaleOrdersRelatedToOpportuntiy(opId, dataSource);
	}

	@Override
	public boolean updateCustomFieldsOfOpprotunity(CrmOpportunity opp) {
		return opDao.updateCustomFieldsOfOpprotunity(opp);
	}

	@Override
	public Map<String, Object> viewOpportunityById(String opId, String userId, MeDataSource dataSource) {
		return opDao.viewOpportunityById(opId, userId, dataSource);
	}

}
