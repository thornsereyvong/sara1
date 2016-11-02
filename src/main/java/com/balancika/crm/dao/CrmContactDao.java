package com.balancika.crm.dao;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmContact;
import com.balancika.crm.model.MeDataSource;

public interface CrmContactDao {

	boolean insertContact(CrmContact contact);
	boolean updateContact(CrmContact contact);
	boolean deleteContact(CrmContact contact);
	List<CrmContact> listContacts(MeDataSource dataSource);
	Object findContactById(String conId, MeDataSource dataSource);
	CrmContact findContactDetailsById(String conId, MeDataSource dataSource);
	List<Object> listContactRelatedToModule(MeDataSource dataSource);
	List<Object> listParentOfContact(MeDataSource dataSource);
	Map<String, Object> viewContact(String conId, MeDataSource dataSource);
	List<CrmContact> listSomeFieldsOfContact(MeDataSource dataSource);
}
