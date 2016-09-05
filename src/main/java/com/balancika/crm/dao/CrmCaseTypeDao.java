package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCaseType;

public interface CrmCaseTypeDao{

	boolean insertCaseType(CrmCaseType type);
	boolean updateCaseType(CrmCaseType type);
	String deleteCaseType(int typeId);
	List<CrmCaseType> listCaseTypes();
	CrmCaseType findCaseTypeById(int typeId);
}
