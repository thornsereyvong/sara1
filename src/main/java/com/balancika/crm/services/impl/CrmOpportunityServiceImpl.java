package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityDao;
import com.balancika.crm.model.CrmOpportunity;
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
	public boolean isDeleteOpportunity(String opId) {
		return opDao.isDeleteOpportunity(opId);
	}

	@Override
	public Object findOpportunityById(String opId) {
		return opDao.findOpportunityById(opId);
	}

	@Override
	public List<CrmOpportunity> listOpportunities() {
		return opDao.listOpportunities();
	}

	@Override
	public CrmOpportunity findOpportunityDetailsById(String opId) {
		return opDao.findOpportunityDetailsById(opId);
	}

	@Override
	public List<Object> listOpportunitiesWithSpecificUser(String username) {
		return opDao.listOpportunitiesWithSpecificUser(username);
	}

	@Override
	public Map<String, Object> listInformationRelateToOpportunity(String opId) {
		return opDao.listInformationRelateToOpportunity(opId);
	}

	@Override
	public List<Object> listContactsRelatedToOpportuntiy(String opId) {
		return opDao.listContactsRelatedToOpportuntiy(opId);
	}

	@Override
	public List<Object> listQuotationsRelatedToOpportuntiy(String opId) {
		return opDao.listQuotationsRelatedToOpportuntiy(opId);
	}

	@Override
	public List<Object> listSaleOrdersRelatedToOpportuntiy(String opId) {
		return opDao.listSaleOrdersRelatedToOpportuntiy(opId);
	}

}
