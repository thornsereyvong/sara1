package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmCaseDao;
import com.balancika.crm.model.CrmCase;
import com.balancika.crm.services.CrmCaseService;

@Service
@Transactional
public class CrmCaseServiceImpl implements CrmCaseService{

	@Autowired
	private CrmCaseDao caseDao;
	
	@Override
	public boolean insertCase(CrmCase cases) {
		try{
			return caseDao.insertCase(cases);
		}catch(HibernateException e){
			return false;
		}
	}

	@Override
	public boolean updateCase(CrmCase cases) {
		try{
			return caseDao.updateCase(cases);
		}catch(HibernateException e){
			return false;
		}
	}

	@Override
	public boolean deleteCase(String caseId) {
		try{
			return caseDao.deleteCase(caseId);
		}catch(HibernateException e){
			return false;
		}
	}

	@Override
	public List<CrmCase> listCases() {
		try{
			return caseDao.listCases();
		}catch(HibernateException e){
			return null;
		}
	}

	@Override
	public Object findCaseById(String caseId) {
		try{
			return caseDao.findCaseById(caseId);
		}catch(HibernateException e){
			return null;
		}
	}

	@Override
	public CrmCase findCaseDetailsById(String caseId) {
		try{
			return caseDao.findCaseDetailsById(caseId);
		}catch(HibernateException e){
			return null;
		}
	}

}
