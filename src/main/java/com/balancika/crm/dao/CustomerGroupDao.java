package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CustomerGroup;
import com.balancika.crm.model.MeDataSource;

public interface CustomerGroupDao {
	List<CustomerGroup> listCustomerGroups(MeDataSource dataSource);
}
