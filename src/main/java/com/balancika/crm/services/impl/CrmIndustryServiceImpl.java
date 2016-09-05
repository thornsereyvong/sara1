package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmIndustryDao;
import com.balancika.crm.model.CrmIndustry;
import com.balancika.crm.services.CrmIndustryService;


@Service
@Transactional
public class CrmIndustryServiceImpl implements CrmIndustryService{

	@Autowired
	private CrmIndustryDao industDao;
	
	@Override
	public boolean insertIndustry(CrmIndustry industry) {
		try{
			return industDao.insertIndustry(industry);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateIndustry(CrmIndustry industry) {
		try{
			return industDao.updateIndustry(industry);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteIndustry(int industID) {
		try{
			return industDao.deleteIndustry(industID);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CrmIndustry> listIndustries() {
		try{
			return industDao.listIndustries();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrmIndustry finIndustryById(int industID) {
		try{
			return industDao.finIndustryById(industID);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
