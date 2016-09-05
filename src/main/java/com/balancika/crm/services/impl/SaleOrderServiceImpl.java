package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.SaleOrderDao;
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
	public boolean deleteSaleOrder(String saleId) {
		return saleOrderDao.deleteSaleOrder(saleId);
	}

	@Override
	public SaleOrder findSaleOrderById(String saleId) {
		return saleOrderDao.findSaleOrderById(saleId);
	}

	@Override
	public List<Object> listSaleOrders() {
		return saleOrderDao.listSaleOrders();
	}

	@Override
	public List<Object> listPrincipleOnStartup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String checkSaleOrderIdExist(String saleId) {
		return saleOrderDao.checkSaleOrderIdExist(saleId);
	}

}
