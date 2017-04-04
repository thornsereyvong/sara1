package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmContactDao;
import com.balancika.crm.model.CrmContact;
import com.balancika.crm.model.MeDataSource;
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
	public boolean deleteContact(CrmContact contact) {
		return contactDao.deleteContact(contact);
	}

	@Override
	public List<CrmContact> listContacts(MeDataSource dataSource) {
		return contactDao.listContacts(dataSource);
	}

	@Override
	public Object findContactById(String conId, MeDataSource dataSource) {
		return contactDao.findContactById(conId, dataSource);
	}

	@Override
	public CrmContact findContactDetailsById(String conId, MeDataSource dataSource) {
		return contactDao.findContactDetailsById(conId, dataSource);
	}

	@Override
	public List<Object> listContactRelatedToModule(MeDataSource dataSource) {
		return contactDao.listContactRelatedToModule(dataSource);
	}

	@Override
	public List<Object> listParentOfContact(MeDataSource dataSource) {
		return contactDao.listParentOfContact(dataSource);
	}

	@Override
	public Map<String, Object> viewContact(String conId,String userId, MeDataSource dataSource) {
		return contactDao.viewContact(conId, userId, dataSource);
	}

	@Override
	public List<CrmContact> listSomeFieldsOfContact(MeDataSource dataSource) {
		return contactDao.listSomeFieldsOfContact(dataSource);
	}

}
