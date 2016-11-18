package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCallDao;
import com.balancika.crm.model.CrmCall;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmCallService;

@Service
@Transactional
public class CrmCallServiceImpl implements CrmCallService{

	@Autowired
	private CrmCallDao callDao;
	
	@Override
	public boolean insertCall(CrmCall call) {
		return callDao.insertCall(call);
	}

	@Override
	public boolean updateCall(CrmCall call) {
		return callDao.updateCall(call);
	}

	@Override
	public boolean deleteCall(CrmCall call) {
		return callDao.deleteCall(call);
	}

	@Override
	public List<CrmCall> listCalls(MeDataSource dataSource) {
		return callDao.listCalls(dataSource);
	}

	@Override
	public Object findCallById(String callId, MeDataSource dataSource) {
		return callDao.findCallById(callId, dataSource);
	}

	@Override
	public CrmCall listCallStructureDetailsById(String callId, MeDataSource dataSource) {
		return callDao.listCallStructureDetailsById(callId, dataSource);
	}

	@Override
	public List<CrmCall> listCallsRelatedToLead(String leadId, MeDataSource dataSource) {
		return callDao.listCallsRelatedToLead(leadId,dataSource);
	}

	@Override
	public List<CrmCall> listCallsRelatedToOpportunity(String opId, MeDataSource dataSource) {
		return callDao.listCallsRelatedToOpportunity(opId,dataSource);
	}

	@Override
	public List<CrmCall> listCallsRelatedToModule(String moduleId, MeDataSource dataSource) {
		return callDao.listCallsRelatedToModule(moduleId,dataSource);
	}

}
