package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.AmeClass;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.PriceCode;

public interface CrmCustomerService {

	boolean insertCustomer(CrmCustomer customer);
	boolean updateCustomer(CrmCustomer customer);
	boolean deleteCustomer(CrmCustomer customer);
	List<CrmCustomer> listCustomers(MeDataSource dataSource);
	CrmCustomer findCustomerById(String custID, MeDataSource dataSource);
	List<Object> listCustomerIdAndName(MeDataSource dataSource);
	List<PriceCode> listPriceCode(MeDataSource dataSource);
	List<AmeClass> listAmeClasses(MeDataSource dataSource);
	CrmCustomer viewCustomerDetails(String custId, MeDataSource dataSource);
}
