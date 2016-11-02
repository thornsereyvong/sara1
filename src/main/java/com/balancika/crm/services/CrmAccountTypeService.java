package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmAccountType;
import com.balancika.crm.model.MeDataSource;

public interface CrmAccountTypeService {

	boolean insertAccountType(CrmAccountType accountType);
	boolean updateAccountType(CrmAccountType accountType);
	boolean deleteAccountType(CrmAccountType accountType);
	List<CrmAccountType> listAccountTypes(MeDataSource dataSource);
	CrmAccountType findAccountTypeById(CrmAccountType accountType);
}
