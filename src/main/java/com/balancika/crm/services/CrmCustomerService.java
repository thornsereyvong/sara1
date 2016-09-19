package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.PriceCode;

public interface CrmCustomerService {

	boolean insertCustomer(CrmCustomer customer);
	boolean updateCustomer(CrmCustomer customer);
	boolean deleteCustomer(String custID);
	List<CrmCustomer> listCustomers();
	CrmCustomer findCustomerById(String custID);
	List<Object> listCustomerIdAndName();
	List<PriceCode> listPriceCode();
	CrmCustomer viewCustomerDetails(String custId);
}
