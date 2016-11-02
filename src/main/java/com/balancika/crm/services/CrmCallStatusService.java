package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCallStatus;
import com.balancika.crm.model.MeDataSource;

public interface CrmCallStatusService {
	boolean insertCallStatus(CrmCallStatus status);
	boolean updateCallStatus(CrmCallStatus status);
	String deleteCallStatus(CrmCallStatus status);
	List<CrmCallStatus> listCallStatus(MeDataSource meDataSource);
	CrmCallStatus findCallStatusById(CrmCallStatus status);
}
