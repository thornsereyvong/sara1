package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCaseStatus;

public interface CrmCaseStatusDao {

	boolean insertCaseStatus(CrmCaseStatus status);
	boolean updateCaseStatus(CrmCaseStatus status);
	String deleteCaseStatus(int statusId);
	List<CrmCaseStatus> listCaseStatus();
	CrmCaseStatus findCaseStatusById(int statusId);
}
