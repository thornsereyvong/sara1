package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmContactDao;
import com.balancika.crm.model.CrmContact;
import com.balancika.crm.services.CrmContactService;

@Service
@Transactional
public class CrmContactServiceImpl implements CrmContactService{

	@Autowired
	private CrmContactDao contactDao;
	
	@Override
	public boolean insertContact(CrmContact contact) {
		try{
			return contactDao.insertContact(contact);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
		
	}

	@Override
	public boolean updateContact(CrmContact contact) {
		try{
			return contactDao.updateContact(contact);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteContact(String conId) {
		try{
			return contactDao.deleteContact(conId);
		}catch(HibernateException e){
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CrmContact> listContacts() {
		try{
			return contactDao.listContacts();
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Object findContactById(String conId) {
		try{
			return contactDao.findContactById(conId);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public CrmContact findContactDetailsById(String conId) {
		try{
			return contactDao.findContactDetailsById(conId);
		}catch(HibernateException e){
			e.printStackTrace();
			return null;
		}
	}

}
