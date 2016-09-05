package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityTypeDao;
import com.balancika.crm.model.CrmOpportunityType;
import com.balancika.crm.services.CrmOpportunityTypeService;

@Service
@Transactional
public class CrmOpportunityTypeServiceImpl implements CrmOpportunityTypeService{
	
	@Autowired
	private CrmOpportunityTypeDao typeDao;

	@Override
	public boolean insertOpportunityType(CrmOpportunityType type) {
		return typeDao.insertOpportunityType(type);
	}

	@Override
	public boolean updateOpportunityType(CrmOpportunityType type) {
		return typeDao.updateOpportunityType(type);
	}

	@Override
	public String deleteOpportunityType(int typeID) {
		return typeDao.deleteOpportunityType(typeID);
	}

	@Override
	public List<CrmOpportunityType> listOpportunityTypes() {
		return typeDao.listOpportunityTypes();
	}

	@Override
	public CrmOpportunityType findOpportunityTypeById(int typeID) {
		return typeDao.findOpportunityTypeById(typeID);
	}
}
