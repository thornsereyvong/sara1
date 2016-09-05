package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCaseStatus;

public interface CrmCaseStatusService {
	boolean insertCaseStatus(CrmCaseStatus status);
	boolean updateCaseStatus(CrmCaseStatus status);
	String deleteCaseStatus(int statusId);
	List<CrmCaseStatus> listCaseStatus();
	CrmCaseStatus findCaseStatusById(int statusId);
}
