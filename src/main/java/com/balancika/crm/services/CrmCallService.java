package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCall;

public interface CrmCallService {
	boolean insertCall(CrmCall call);
	boolean updateCall(CrmCall call);
	boolean deleteCall(String callId);
	List<CrmCall> listCalls();
	Object findCallById(String callId);
	CrmCall listCallStructureDetailsById(String callId);
	List<CrmCall> listCallsRelatedToLead(String leadId);
	List<CrmCall> listCallsRelatedToOpportunity(String opId);
}
