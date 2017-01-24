package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.model.AmeClass;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.PriceCode;
import com.balancika.crm.services.CrmCustomerService;

@Service
@Transactional
public class CrmCustomerServiceImpl implements CrmCustomerService{
	
	@Autowired
	private CrmCustomerDao customerDao;

	@Override
	public boolean insertCustomer(CrmCustomer customer) {
		return customerDao.insertCustomer(customer);
	}

	@Override
	public boolean updateCustomer(CrmCustomer customer) {
		return customerDao.updateCustomer(customer);
	}

	@Override
	public boolean deleteCustomer(CrmCustomer customer) {
		return customerDao.deleteCustomer(customer);
	}

	@Override
	public List<CrmCustomer> listCustomers(MeDataSource dataSource) {
		return customerDao.listCustomers(dataSource);
	}

	@Override
	public CrmCustomer findCustomerById(String custID, MeDataSource dataSource) {
		return customerDao.findCustomerById(custID, dataSource);
	}

	@Override
	public List<Object> listCustomerIdAndName(MeDataSource dataSource) {
		return customerDao.listCustomerIdAndName(dataSource);
	}

	@Override
	public List<PriceCode> listPriceCode(MeDataSource dataSource) {
		return customerDao.listPriceCode(dataSource);
	}

	@Override
	public CrmCustomer viewCustomerDetails(String custId, MeDataSource dataSource) {
		return customerDao.viewCustomerDetails(custId, dataSource);
	}

	@Override
	public List<AmeClass> listAmeClasses(MeDataSource dataSource) {
		return customerDao.listAmeClasses(dataSource);
	}

	@Override
	public List<Object> creditInfo(String custId, MeDataSource dataSource) {		
		return customerDao.creditInfo(custId, dataSource);
	}

	@Override
	public Map<String, Object> creditInfoByCustomer(String custId, MeDataSource dataSource) {
		return customerDao.creditInfoByCustomer(custId, dataSource);
	}

}
