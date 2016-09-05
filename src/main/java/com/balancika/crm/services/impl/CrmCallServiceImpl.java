package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCallDao;
import com.balancika.crm.model.CrmCall;
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
	public boolean deleteCall(String callId) {
		return callDao.deleteCall(callId);
	}

	@Override
	public List<CrmCall> listCalls() {
		return callDao.listCalls();
	}

	@Override
	public Object findCallById(String callId) {
		return callDao.findCallById(callId);
	}

	@Override
	public CrmCall listCallStructureDetailsById(String callId) {
		return callDao.listCallStructureDetailsById(callId);
	}

}
