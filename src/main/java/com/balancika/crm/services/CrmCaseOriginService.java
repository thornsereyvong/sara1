package com.balancika.crm.services;


import java.util.List;

import com.balancika.crm.model.CrmCaseOrigin;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseOriginService{
	boolean addCaseOrigin(CrmCaseOrigin origin);
	boolean updateCaseOrigin(CrmCaseOrigin origin);
	boolean deleteCaseOrigin(CrmCaseOrigin origin);
	CrmCaseOrigin findCaseOriginById(int originId, MeDataSource dataSource);
	List<CrmCaseOrigin> listCaseOrigins(MeDataSource dataSource);
}
