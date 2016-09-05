package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCase;

public interface CrmCaseDao {

	boolean insertCase(CrmCase cases);
	boolean updateCase(CrmCase cases);
	boolean deleteCase(String caseId);
	List<CrmCase> listCases();
	Object findCaseById(String caseId);
	CrmCase findCaseDetailsById(String caseId);
}
