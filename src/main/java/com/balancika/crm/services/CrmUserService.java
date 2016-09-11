package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmUser;

public interface CrmUserService {

	boolean isInserted(CrmUser user);
	boolean isUpdated(CrmUser user);
	boolean isDeleted(String userId);
	CrmUser findUserByUsername(String username);
	List<CrmUser> listAllUsers();
	CrmUser findUserById(String userId);
	List<CrmUser> listSubordinateUserByUsername(String username);
	CrmUser webLogin(String username);
	String checkChildOfUser(String username);
	List<Object> listAllUsernameAndId();
}
