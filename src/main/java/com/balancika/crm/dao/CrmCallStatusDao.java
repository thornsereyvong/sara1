package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCallStatus;
import com.balancika.crm.model.MeDataSource;

public interface CrmCallStatusDao {
	boolean insertCallStatus(CrmCallStatus status);
	boolean updateCallStatus(CrmCallStatus status);
	String deleteCallStatus(CrmCallStatus status);
	List<CrmCallStatus> listCallStatus(MeDataSource meDataSource);
	CrmCallStatus findCallStatusById(int statusId, MeDataSource dataSource);
}
