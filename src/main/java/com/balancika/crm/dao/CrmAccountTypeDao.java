package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmAccountType;

public interface CrmAccountTypeDao {

	boolean insertAccountType(CrmAccountType accountType);
	boolean updateAccountType(CrmAccountType accountType);
	boolean deleteAccountType(int accountTypeID);
	List<CrmAccountType> listAccountTypes();
	CrmAccountType findAccountTypeById(int accountTypeId);
}
