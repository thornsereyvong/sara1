package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CustomerGroupDao;
import com.balancika.crm.model.CustomerGroup;
import com.balancika.crm.services.CustomerGroupService;

@Service
@Transactional
public class CustomerGroupServiceImpl implements CustomerGroupService{

	@Autowired
	private CustomerGroupDao groupDao;
	
	@Override
	public List<CustomerGroup> listCustomerGroups() {
		return groupDao.listCustomerGroups();
	}

}
