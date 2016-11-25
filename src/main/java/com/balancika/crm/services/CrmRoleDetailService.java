package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmRoleDetail;
import com.balancika.crm.model.MeDataSource;

public interface CrmRoleDetailService {
	boolean insertRoleDetail(CrmRoleDetail roleDetail);
	 boolean updateRoleDetail(CrmRoleDetail roleDetail);
	 boolean deleteRoleDetail(CrmRoleDetail roleDetail);
	 List<CrmRoleDetail> listRoleDetails(MeDataSource dataSource);
	 CrmRoleDetail findRoleDetailById(int roleDetailId, MeDataSource dataSource);
	 CrmRoleDetail findRoleDetail(MeDataSource dataSource);
	 Object findRoleDetailsByUsername(String username, String moduleId, MeDataSource dataSource);
}
