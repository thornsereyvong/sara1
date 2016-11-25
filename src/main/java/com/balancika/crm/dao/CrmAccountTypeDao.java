package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmAccountType;
import com.balancika.crm.model.MeDataSource;

public interface CrmAccountTypeDao {

	boolean insertAccountType(CrmAccountType accountType);
	boolean updateAccountType(CrmAccountType accountType);
	boolean deleteAccountType(CrmAccountType accountType);
	List<CrmAccountType> listAccountTypes(MeDataSource dataSource);
	CrmAccountType findAccountTypeById(int typeId, MeDataSource dataSource);
}
