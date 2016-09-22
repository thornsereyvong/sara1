package com.balancika.crm.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmOpportunitySaleOrderDao;
import com.balancika.crm.model.CrmOpportunitySaleOrder;

@Repository
public class CrmOpportunitySaleOrderDaoImpl implements CrmOpportunitySaleOrderDao{

	@Autowired
	private HibernateTransactionManager transactionManager;
	
	@Override
	public boolean insertOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteOpportunitySaleOrder(int opSaleOrderId) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public CrmOpportunitySaleOrder findOpportunitySaleOrder(int opSaleOrderId) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
