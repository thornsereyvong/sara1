package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

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
		return contactDao.insertContact(contact);
		
	}

	@Override
	public boolean updateContact(CrmContact contact) {
		return contactDao.updateContact(contact);
	}

	@Override
	public boolean deleteContact(String conId) {
		return contactDao.deleteContact(conId);
	}

	@Override
	public List<CrmContact> listContacts() {
		return contactDao.listContacts();
	}

	@Override
	public Object findContactById(String conId) {
		return contactDao.findContactById(conId);
	}

	@Override
	public CrmContact findContactDetailsById(String conId) {
		return contactDao.findContactDetailsById(conId);
	}

	@Override
	public List<Object> listContactRelatedToModule() {
		return contactDao.listContactRelatedToModule();
	}

	@Override
	public List<Object> listParentOfContact() {
		return contactDao.listParentOfContact();
	}

	@Override
	public Map<String, Object> viewContact(String conId) {
		return contactDao.viewContact(conId);
	}

}
