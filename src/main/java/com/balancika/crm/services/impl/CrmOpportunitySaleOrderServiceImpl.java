package com.balancika.crm.services.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunitySaleOrderDao;
import com.balancika.crm.model.CrmOpportunitySaleOrder;
import com.balancika.crm.model.MeDataSource;
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
	public boolean deleteOpportunitySaleOrder(CrmOpportunitySaleOrder opSaleOrder) {
		return opportunitySaleOrderDao.deleteOpportunitySaleOrder(opSaleOrder);
	}

	@Override
	public CrmOpportunitySaleOrder findOpportunitySaleOrder(int opSaleOrderId, MeDataSource dataSource) {
		return opportunitySaleOrderDao.findOpportunitySaleOrder(opSaleOrderId, dataSource);
	}

	@Override
	public Integer checkOpportunitySaleOrderIsExist(String opId, String saleOrderId, MeDataSource dataSource) {
		return opportunitySaleOrderDao.checkOpportunitySaleOrderIsExist(opId, saleOrderId, dataSource);
	}

	@Override
	public Object viewOpportunitySaleOrder(int opSaleOrderId, MeDataSource dataSource) {
		return opportunitySaleOrderDao.viewOpportunitySaleOrder(opSaleOrderId, dataSource);
	}

}
