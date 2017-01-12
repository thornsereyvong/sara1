package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmRole;
import com.balancika.crm.model.MeDataSource;

public interface CrmRoleService {
	boolean isInsertedRole(CrmRole role);
	boolean isUpdatedRole(CrmRole role);
	boolean isDeletedRole(CrmRole role);     
	CrmRole findRoleById(String roleId, MeDataSource dataSource);
	List<Object> findRoleDetailsByRoleId(String roleId, MeDataSource dataSource);
	List<CrmRole> listAllRoles(MeDataSource dataSource);
	List<Object> findRoleMaster(MeDataSource dataSource);
	CrmRole findRoleByUsername(String username, MeDataSource dataSource);
}
