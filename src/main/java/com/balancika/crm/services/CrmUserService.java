package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.MeDataSource;

public interface CrmUserService {

	boolean isInserted(CrmUser user);
	boolean isUpdated(CrmUser user);
	boolean isDeleted(CrmUser user);
	CrmUser findUserByUsername(String username, MeDataSource dataSource);
	List<CrmUser> listAllUsers(MeDataSource meDataSource);
	CrmUser findUserById(String userId, MeDataSource dataSource);
	List<CrmUser> listSubordinateUserByUsername(String username, MeDataSource dataSource);
	CrmUser webLogin(CrmUser user);
	String checkChildOfUser(String username, MeDataSource dataSource);
	List<Object> listAllUsernameAndId(MeDataSource dataSource);
}
