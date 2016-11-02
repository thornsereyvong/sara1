package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseService {

	boolean insertCase(CrmCase cases);
	boolean updateCase(CrmCase cases);
	boolean deleteCase(CrmCase cases);
	List<CrmCase> listCases(MeDataSource dataSource);
	Object findCaseById(String caseId, MeDataSource dataSource);
	CrmCase findCaseDetailsById(String caseId, MeDataSource dataSource);
}
