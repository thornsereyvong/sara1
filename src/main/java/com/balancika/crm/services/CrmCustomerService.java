package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCustomer;

public interface CrmCustomerService {

	boolean insertCustomer(CrmCustomer customer);
	boolean updateCustomer(CrmCustomer customer);
	boolean deleteCustomer(String custID);
	List<CrmCustomer> listCustomers();
	CrmCustomer findCustomerById(String custID);
}
