package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCall;
import com.balancika.crm.model.MeDataSource;

public interface CrmCallService {
	boolean insertCall(CrmCall call);
	boolean updateCall(CrmCall call);
	boolean deleteCall(CrmCall call);
	List<CrmCall> listCalls(MeDataSource dataSource);
	Object findCallById(String callId, MeDataSource dataSource);
	CrmCall listCallStructureDetailsById(String callId, MeDataSource dataSource);
	List<CrmCall> listCallsRelatedToLead(String leadId, MeDataSource dataSource);
	List<CrmCall> listCallsRelatedToOpportunity(String opId, MeDataSource dataSource);
	List<CrmCall> listCallsRelatedToModule(String moduleId, MeDataSource dataSource);
}
