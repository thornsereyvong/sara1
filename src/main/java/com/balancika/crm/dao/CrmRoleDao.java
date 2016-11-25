package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmRole;
import com.balancika.crm.model.MeDataSource;

public interface CrmRoleDao{
	boolean isInsertedRole(CrmRole role);
	boolean isUpdatedRole(CrmRole role);
	boolean isDeletedRole(CrmRole role);
	CrmRole findRoleById(String roleId, MeDataSource dataSource);
	List<CrmRole> listAllRoles(MeDataSource dataSource);
	CrmRole findRoleByUsername(String username, MeDataSource dataSource);
}
