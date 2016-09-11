package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmUserDao;
import com.balancika.crm.model.CrmUser;
import com.balancika.crm.services.CrmUserService;

@Service("CrmUserService")
@Transactional
public class CrmUserServiceImpl implements CrmUserService{

	@Autowired
	@Qualifier("CrmUserDao")
	private CrmUserDao userDao;
	
	@Override
	public boolean isInserted(CrmUser user) {
		return userDao.isInserted(user);
		
	}

	@Override
	public boolean isUpdated(CrmUser user) {
		return userDao.isUpdated(user);
	}

	@Override
	public boolean isDeleted(String userId) {
		return userDao.isDeleted(userId);
	}

	@Override
	public CrmUser findUserByUsername(String username) {
		return userDao.findUserByUsername(username);
	}

	@Override
	public List<CrmUser> listAllUsers() {
		return userDao.listAllUsers();
	}

	@Override
	public CrmUser findUserById(String userId) {
		return userDao.findUserById(userId);
	}

	@Override
	public List<CrmUser> listSubordinateUserByUsername(String username) {
		return userDao.listSubordinateUserByUsername(username);
	}

	@Override
	public CrmUser webLogin(String username) {
		return userDao.webLogin(username);
	}

	@Override
	public String checkChildOfUser(String username) {
		return userDao.checkChildOfUser(username);
	}

	@Override
	public List<Object> listAllUsernameAndId() {
		return userDao.listAllUsernameAndId();
	}
}
