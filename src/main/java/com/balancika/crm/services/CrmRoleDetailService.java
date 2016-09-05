package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmRoleDetail;

public interface CrmRoleDetailService {
	 boolean insertRoleDetail(CrmRoleDetail roleDetail);
	 boolean updateRoleDetail(CrmRoleDetail roleDetail);
	 boolean deleteRoleDetail(int roleDetailId);
	 List<CrmRoleDetail> listRoleDetails();
	 CrmRoleDetail findRoleDetailById(int roleDetailId);
	 Object findRoleDetailsByUsername(String username,String moduleId);
}
