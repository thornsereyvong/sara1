package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.MeDataSource;

public interface CrmUserService {

	boolean isInserted(CrmUser user);
	boolean isUpdated(CrmUser user);
	boolean isDeleted(CrmUser user);
	CrmUser findUserByUsername(CrmUser user);
	List<CrmUser> listAllUsers(MeDataSource meDataSource);
	CrmUser findUserById(CrmUser user);
	List<CrmUser> listSubordinateUserByUsername(CrmUser user);
	CrmUser webLogin(CrmUser user);
	String checkChildOfUser(CrmUser user);
	List<Object> listAllUsernameAndId(MeDataSource dataSource);
}
