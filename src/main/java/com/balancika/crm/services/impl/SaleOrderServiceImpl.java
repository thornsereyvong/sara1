package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.SaleOrderDao;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.model.SaleOrder;
import com.balancika.crm.services.SaleOrderService;

@Service
@Transactional
public class SaleOrderServiceImpl implements SaleOrderService{

	@Autowired
	private SaleOrderDao saleOrderDao;

	@Override
	public boolean insertSaleOrder(SaleOrder saleOrder) {
		return saleOrderDao.insertSaleOrder(saleOrder);
	}

	@Override
	public boolean updateSaleOrder(SaleOrder saleOrder) {
		return saleOrderDao.updateSaleOrder(saleOrder);
	}

	@Override
	public boolean deleteSaleOrder(SaleOrder saleOrder) {
		return saleOrderDao.deleteSaleOrder(saleOrder);
	}

	@Override
	public SaleOrder findSaleOrderById(String saleId, MeDataSource dataSource) {
		return saleOrderDao.findSaleOrderById(saleId, dataSource);
	}

	@Override
	public List<Object> listSaleOrders(MeDataSource dataSource) {
		return saleOrderDao.listSaleOrders(dataSource);
	}

	@Override
	public List<Object> listPrincipleOnStartup(MeDataSource dataSource) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkSaleOrderIdExist(String saleId, MeDataSource dataSource) {
		return saleOrderDao.checkSaleOrderIdExist(saleId, dataSource);
	}

	@Override
	public boolean updateSaleOrderPostStatus(String saleId, String status, MeDataSource dataSource) {
		return saleOrderDao.updateSaleOrderPostStatus(saleId, status, dataSource);
	}

}
