package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.dao.CrmCaseOriginDao;
import com.balancika.crm.model.CrmCaseOrigin;
import com.balancika.crm.model.MeDataSource;

public interface CrmCaseOriginService extends CrmCaseOriginDao{
	@Override
	public boolean addCaseOrigin(CrmCaseOrigin origin);

	@Override
	public boolean updateCaseOrigin(CrmCaseOrigin origin);
	
	@Override
	public boolean deleteCaseOrigin(CrmCaseOrigin origin);
	
	@Override
	public CrmCaseOrigin findCaseOriginById(int originId, MeDataSource dataSource);
	
	@Override
	public List<CrmCaseOrigin> listCaseOrigins(MeDataSource dataSource);
}
