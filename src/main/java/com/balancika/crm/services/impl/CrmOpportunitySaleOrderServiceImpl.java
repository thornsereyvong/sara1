package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunitySaleOrderDao;
import com.balancika.crm.model.CrmOpportunitySaleOrder;
import com.balancika.crm.services.CrmOpportunitySaleOrderService;

@Service
@Transactional
public class CrmOpportunitySaleOrderServiceImpl implements CrmOpportunitySaleOrderService{

	@Autowired
	private CrmOpportunitySaleOrderDao opportunitySaleOrderDao;
	
	@Override
	public boolean insertOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		return opportunitySaleOrderDao.insertOpportunitySaleOrder(opSaleOrder);
	}

	@Override
	public boolean updateOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		return opportunitySaleOrderDao.updateOpportunitySaleOrder(opSaleOrder);
	}

	@Override
	public boolean deleteOpportunitySaleOrder(int opSaleOrderId) {
		return opportunitySaleOrderDao.deleteOpportunitySaleOrder(opSaleOrderId);
	}

	@Override
	public CrmOpportunitySaleOrder findOpportunitySaleOrder(int opSaleOrderId) {
		return opportunitySaleOrderDao.findOpportunitySaleOrder(opSaleOrderId);
	}

}
