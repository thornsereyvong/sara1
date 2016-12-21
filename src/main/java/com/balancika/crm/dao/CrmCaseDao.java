package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCase;
import com.balancika.crm.model.CrmCaseSolution;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseDao {

	boolean insertCase(CrmCase cases);
	boolean updateCase(CrmCase cases);
	boolean deleteCase(CrmCase cases);
	List<CrmCase> listCases(MeDataSource dataSource);
	Object findCaseById(String caseId, MeDataSource dataSource);
	CrmCase findCaseDetailsById(String caseId, MeDataSource dataSource);
	boolean updateCustomFieldOfCase(CrmCase cases);
	boolean updateCase(CrmCaseSolution cases);
}
