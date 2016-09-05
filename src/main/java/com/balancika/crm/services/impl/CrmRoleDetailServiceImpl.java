package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmRoleDetailDao;
import com.balancika.crm.model.CrmRoleDetail;
import com.balancika.crm.services.CrmRoleDetailService;

@Service
@Transactional
public class CrmRoleDetailServiceImpl implements CrmRoleDetailService{

	@Autowired
	private CrmRoleDetailDao roleDetailDao;
	
	@Override
	public boolean insertRoleDetail(CrmRoleDetail roleDetail) {
		return roleDetailDao.insertRoleDetail(roleDetail);
		
	}

	@Override
	public boolean updateRoleDetail(CrmRoleDetail roleDetail) {
		return	roleDetailDao.updateRoleDetail(roleDetail);
	}

	@Override
	public boolean deleteRoleDetail(int roleDetailId) {
		return roleDetailDao.deleteRoleDetail(roleDetailId); 
	}

	@Override
	public List<CrmRoleDetail> listRoleDetails() {
		return roleDetailDao.listRoleDetails();
	}

	@Override
	public CrmRoleDetail findRoleDetailById(int roleDetailId) {
		return roleDetailDao.findRoleDetailById(roleDetailId);
	}

	@Override
	public Object findRoleDetailsByUsername(String username,String moduleId) {
		
		return roleDetailDao.findRoleDetailsByUsername(username,moduleId);
	}

}
