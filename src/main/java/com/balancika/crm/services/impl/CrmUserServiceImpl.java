package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmUserDao;
import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.CrmUserLogin;
import com.balancika.crm.model.MeDataSource;
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
	public boolean isDeleted(CrmUser user) {
		return userDao.isDeleted(user);
	}

	@Override
	public CrmUser findUserByUsername(String username, MeDataSource dataSource) {
		return userDao.findUserByUsername(username, dataSource);
	}

	@Override
	public List<CrmUser> listAllUsers(MeDataSource dataSource) {
		return userDao.listAllUsers(dataSource);
	}

	@Override
	public CrmUser findUserById(String userID, MeDataSource dataSource) {
		return userDao.findUserById(userID , dataSource);
	}

	@Override
	public List<CrmUser> listSubordinateUserByUsername(String username, MeDataSource dataSource) {
		return userDao.listSubordinateUserByUsername(username, dataSource);
	}

	@Override
	public CrmUserLogin webLogin(CrmUserLogin user) {
		return userDao.webLogin(user);
	}

	@Override
	public String checkChildOfUser(String username, MeDataSource dataSource) {
		return userDao.checkChildOfUser(username ,dataSource);
	}

	@Override
	public List<Object> listAllUsernameAndId(MeDataSource dataSource) {
		return userDao.listAllUsernameAndId(dataSource);
	}
}
