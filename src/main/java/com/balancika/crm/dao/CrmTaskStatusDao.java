package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmTaskStatus;
import com.balancika.crm.model.MeDataSource;

public interface CrmTaskStatusDao {

	boolean insertTaskStatus(CrmTaskStatus status);
	boolean updateTaskStatus(CrmTaskStatus status);
	String deleteTaskStatus(CrmTaskStatus status);
	List<CrmTaskStatus> lisTaskStatus(MeDataSource dataSource);
	CrmTaskStatus findTaskStatusById(int statusId, MeDataSource dataSource);
}
