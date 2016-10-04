package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.AmeClass;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.PriceCode;

public interface CrmCustomerDao {

	boolean insertCustomer(CrmCustomer customer);
	boolean updateCustomer(CrmCustomer customer);
	boolean deleteCustomer(String custID);
	List<CrmCustomer> listCustomers();
	CrmCustomer findCustomerById(String custID);
	List<Object> listCustomerIdAndName();
	List<PriceCode> listPriceCode();
	List<AmeClass> listAmeClasses();
	CrmCustomer viewCustomerDetails(String custId);
}
