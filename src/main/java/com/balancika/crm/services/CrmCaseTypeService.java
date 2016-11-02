package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCaseType;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseTypeService {
	boolean insertCaseType(CrmCaseType type);
	boolean updateCaseType(CrmCaseType type);
	String deleteCaseType(CrmCaseType type);
	List<CrmCaseType> listCaseTypes(MeDataSource dataSource);
	CrmCaseType findCaseTypeById(int typeId, MeDataSource dataSource);
}
