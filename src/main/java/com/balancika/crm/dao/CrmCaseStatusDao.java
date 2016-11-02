package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCaseStatus;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseStatusDao {

	boolean insertCaseStatus(CrmCaseStatus status);
	boolean updateCaseStatus(CrmCaseStatus status);
	String deleteCaseStatus(CrmCaseStatus status);
	List<CrmCaseStatus> listCaseStatus(MeDataSource dataSource);
	CrmCaseStatus findCaseStatusById(int statusId, MeDataSource dataSource);
}
