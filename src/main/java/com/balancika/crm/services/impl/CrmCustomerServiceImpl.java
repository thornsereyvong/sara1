package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCustomerDao;
import com.balancika.crm.model.CrmCustomer;
import com.balancika.crm.services.CrmCustomerService;

@Service
@Transactional
public class CrmCustomerServiceImpl implements CrmCustomerService{
	
	@Autowired
	private CrmCustomerDao customerDao;

	@Override
	public boolean insertCustomer(CrmCustomer customer) {
		try{
			return customerDao.insertCustomer(customer);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCustomer(CrmCustomer customer) {
		try{
			return customerDao.updateCustomer(customer);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteCustomer(String custID) {
		try{
			return customerDao.deleteCustomer(custID);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CrmCustomer> listCustomers() {
		try{
			return customerDao.listCustomers();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrmCustomer findCustomerById(String custID) {
		try{
			return customerDao.findCustomerById(custID);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
