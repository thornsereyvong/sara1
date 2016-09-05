package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmRole;

public interface CrmRoleDao{
	boolean isInsertedRole(CrmRole role);
	boolean isUpdatedRole(CrmRole role);
	boolean isDeletedRole(String roleId);
	CrmRole findRoleById(String roleId);
	List<CrmRole> listAllRoles();
}
