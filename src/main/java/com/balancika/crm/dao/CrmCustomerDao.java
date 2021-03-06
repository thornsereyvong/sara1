package com.balancika.crm.dao;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.AmeClass;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.PriceCode;

public interface CrmCustomerDao {

	boolean insertCustomer(CrmCustomer customer);
	boolean updateCustomer(CrmCustomer customer);
	boolean deleteCustomer(CrmCustomer customer);
	List<CrmCustomer> listCustomers(MeDataSource dataSource);
	CrmCustomer findCustomerById(String custID, MeDataSource dataSource);
	List<Object> listCustomerIdAndName(MeDataSource dataSource);
	List<PriceCode> listPriceCode(MeDataSource dataSource);
	List<AmeClass> listAmeClasses(MeDataSource dataSource);
	List<Object> creditInfo(String custId, MeDataSource dataSource);
	Map<String, Object> creditInfoByCustomer(String custId, MeDataSource dataSource);
	Map<String, Object> viewCustomerById(String custId, String userId, MeDataSource dataSource);
}
