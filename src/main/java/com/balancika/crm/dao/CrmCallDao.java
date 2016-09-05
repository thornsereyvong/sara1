package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCall;

public interface CrmCallDao {
	boolean insertCall(CrmCall call);
	boolean updateCall(CrmCall call);
	boolean deleteCall(String callId);
	List<CrmCall> listCalls();
	Object findCallById(String callId);
	CrmCall listCallStructureDetailsById(String callId);
}
