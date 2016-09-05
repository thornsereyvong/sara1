package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCaseType;

public interface CrmCaseTypeService {
	boolean insertCaseType(CrmCaseType type);
	boolean updateCaseType(CrmCaseType type);
	String deleteCaseType(int typeId);
	List<CrmCaseType> listCaseTypes();
	CrmCaseType findCaseTypeById(int typeId);
}
