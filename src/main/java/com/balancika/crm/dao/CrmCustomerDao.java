package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmCustomer;

public interface CrmCustomerDao {

	boolean insertCustomer(CrmCustomer customer);
	boolean updateCustomer(CrmCustomer customer);
	boolean deleteCustomer(String custID);
	List<CrmCustomer> listCustomers();
	CrmCustomer findCustomerById(String custID);
}
