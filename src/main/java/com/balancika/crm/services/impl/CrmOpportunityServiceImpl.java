package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmOpportunityDao;
import com.balancika.crm.model.CrmOpportunity;
import com.balancika.crm.services.CrmOpportunityService;


@Service
@Transactional
public class CrmOpportunityServiceImpl implements CrmOpportunityService{

	@Autowired
	private CrmOpportunityDao opDao;
	
	@Override
	public boolean isInsertOpportunity(CrmOpportunity opportunity) {
		try{
			return opDao.isInsertOpportunity(opportunity);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isUpdateOpportunity(CrmOpportunity opportunity) {
		try{
			return opDao.isUpdateOpportunity(opportunity);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean isDeleteOpportunity(String opId) {
		try{
			return opDao.isDeleteOpportunity(opId);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public Object findOpportunityById(String opId) {
		try{
			return opDao.findOpportunityById(opId);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<CrmOpportunity> listOpportunities() {
		try{
			return opDao.listOpportunities();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrmOpportunity findOpportunityDetailsById(String opId) {
		try{
			return opDao.findOpportunityDetailsById(opId);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
