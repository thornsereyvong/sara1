package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
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
		
		try{
			return userDao.isInserted(user);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean isUpdated(CrmUser user) {
		try{
			return userDao.isUpdated(user);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isDeleted(String userId) {
		try{
			return userDao.isDeleted(userId);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public CrmUser findUserByUsername(String username) {
		try{
			return userDao.findUserByUsername(username);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CrmUser> listAllUsers() {
		try{
			return userDao.listAllUsers();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrmUser findUserById(String userId) {
		try{
			return userDao.findUserById(userId);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CrmUser> listSubordinateUserByUsername(String username) {
		try{
			return userDao.listSubordinateUserByUsername(username);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrmUser webLogin(String username) {
		
		try{
			return userDao.webLogin(username);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}
}
