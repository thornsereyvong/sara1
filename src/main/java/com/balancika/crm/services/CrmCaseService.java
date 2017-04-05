package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.CrmCaseSolution;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseService {

	boolean insertCase(CrmCase cases);
	boolean updateCase(CrmCase cases);
	boolean deleteCase(CrmCase cases);
	List<CrmCase> listCases(MeDataSource dataSource);
	Object findCaseById(String caseId, MeDataSource dataSource);
	CrmCase findCaseDetailsById(String caseId, MeDataSource dataSource);
	boolean updateCustomFieldOfCase(CrmCase cases);
	boolean updateCase(CrmCaseSolution cases);
	Map<String, Object> viewCaseById(String caseId, String userId, MeDataSource dataSource);
}
