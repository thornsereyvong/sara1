package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCallStatus;

public interface CrmCallStatusDao {
	boolean insertCallStatus(CrmCallStatus status);
	boolean updateCallStatus(CrmCallStatus status);
	String deleteCallStatus(int statusId);
	List<CrmCallStatus> listCallStatus();
	CrmCallStatus findCallStatusById(int statusId);
}
