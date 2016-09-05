package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmRoleDao;
import com.balancika.crm.model.CrmRole;
import com.balancika.crm.services.CrmRoleService;

@Service("CrmRoleService")
@Transactional
public class CrmRoleServiceImpl implements CrmRoleService{

	@Autowired
	@Qualifier("CrmRoleDao")
	private CrmRoleDao roleDao;
	
	@Override
	public boolean isInsertedRole(CrmRole role) {
		try{
			return roleDao.isInsertedRole(role);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean isUpdatedRole(CrmRole role) {
		try{
			return roleDao.isUpdatedRole(role);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isDeletedRole(String roleId) {
		try{
			return roleDao.isDeletedRole(roleId);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CrmRole findRoleById(String roleId) {
		try{
			return roleDao.findRoleById(roleId);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CrmRole> listAllRoles() {
		try{
			return roleDao.listAllRoles();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
