package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCaseStatus;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseStatusService {
	boolean insertCaseStatus(CrmCaseStatus status);
	boolean updateCaseStatus(CrmCaseStatus status);
	String deleteCaseStatus(CrmCaseStatus status);
	List<CrmCaseStatus> listCaseStatus(MeDataSource dataSource);
	CrmCaseStatus findCaseStatusById(int statusId, MeDataSource dataSource);
}
