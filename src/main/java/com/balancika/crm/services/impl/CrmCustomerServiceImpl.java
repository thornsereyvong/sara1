package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.model.CrmCustomer;
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
	public boolean deleteCustomer(String custID) {
		return customerDao.deleteCustomer(custID);
	}

	@Override
	public List<CrmCustomer> listCustomers() {
		return customerDao.listCustomers();
	}

	@Override
	public CrmCustomer findCustomerById(String custID) {
		return customerDao.findCustomerById(custID);
	}

	@Override
	public List<Object> listCustomerIdAndName() {
		return customerDao.listCustomerIdAndName();
	}

	@Override
	public List<PriceCode> listPriceCode() {
		return customerDao.listPriceCode();
	}

}
