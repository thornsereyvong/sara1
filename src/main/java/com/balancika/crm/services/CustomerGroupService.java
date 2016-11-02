package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CustomerGroup;
import com.balancika.crm.model.MeDataSource;

public interface CustomerGroupService {
	List<CustomerGroup> listCustomerGroups(MeDataSource dataSource);
}
