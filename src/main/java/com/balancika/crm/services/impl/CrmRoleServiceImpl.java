package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmRoleDao;
import com.balancika.crm.model.CrmRole;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmRoleService;

@Service("CrmRoleService")
@Transactional
public class CrmRoleServiceImpl implements CrmRoleService{

	@Autowired
	@Qualifier("CrmRoleDao")
	private CrmRoleDao roleDao;
	
	@Override
	public boolean isInsertedRole(CrmRole role) {
		return roleDao.isInsertedRole(role);
	}

	@Override
	public boolean isUpdatedRole(CrmRole role) {
		return roleDao.isUpdatedRole(role);
	}

	@Override
	public boolean isDeletedRole(CrmRole role) {
		return roleDao.isDeletedRole(role);
	}

	@Override
	public CrmRole findRoleById(String roleId, MeDataSource dataSource) {
		return roleDao.findRoleById(roleId, dataSource);
	}

	@Override
	public List<CrmRole> listAllRoles(MeDataSource dataSource) {
		return roleDao.listAllRoles(dataSource);
	}

	@Override
	public CrmRole findRoleByUsername(String username, MeDataSource dataSource) {
		return roleDao.findRoleByUsername(username, dataSource);
	}

	@Override
	public List<Object> findRoleDetailsByRoleId(String roleId, MeDataSource dataSource) {
		return roleDao.findRoleDetailsByRoleId(roleId, dataSource);
	}

	@Override
	public List<Object> findRoleMaster(MeDataSource dataSource) {
		
		return roleDao.findRoleMaster(dataSource);
	}

}
