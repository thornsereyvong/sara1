package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmUser;

public interface CrmUserDao {
	
	boolean isInserted(CrmUser user);
	boolean isUpdated(CrmUser user);
	boolean isDeleted(String userId);
	CrmUser findUserByUsername(CrmUser user);
	List<CrmUser> listAllUsers();
	CrmUser findUserById(String userId);
	List<CrmUser> listSubordinateUserByUsername(String username);
	CrmUser webLogin(CrmUser user);
	String checkChildOfUser(String username);
	List<Object> listAllUsernameAndId();
}
