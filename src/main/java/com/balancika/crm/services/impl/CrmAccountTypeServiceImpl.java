package com.balancika.crm.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.balancika.crm.dao.CrmAccountTypeDao;
import com.balancika.crm.model.CrmAccountType;
import com.balancika.crm.services.CrmAccountTypeService;

@Service
@Transactional
public class CrmAccountTypeServiceImpl implements CrmAccountTypeService {

	@Autowired
	private CrmAccountTypeDao accountTypeDao;

	@Override
	public boolean insertAccountType(CrmAccountType accountType) {
		return accountTypeDao.insertAccountType(accountType);
	}

	@Override
	public boolean updateAccountType(CrmAccountType accountType) {
		return accountTypeDao.updateAccountType(accountType);
	}

	@Override
	public boolean deleteAccountType(int accountTypeID) {
		return accountTypeDao.deleteAccountType(accountTypeID);
	}

	@Override
	public List<CrmAccountType> listAccountTypes() {
		return accountTypeDao.listAccountTypes();
	}

	@Override
	public CrmAccountType findAccountTypeById(int accountTypeId) {
		return accountTypeDao.findAccountTypeById(accountTypeId);
	}
}
