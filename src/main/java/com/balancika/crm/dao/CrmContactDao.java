package com.balancika.crm.dao;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmContact;

public interface CrmContactDao {

	boolean insertContact(CrmContact contact);
	boolean updateContact(CrmContact contact);
	boolean deleteContact(String conId );
	List<CrmContact> listContacts();
	Object findContactById(String conId);
	CrmContact findContactDetailsById(String conId);
	List<Object> listContactRelatedToModule();
	List<Object> listParentOfContact();
	Map<String, Object> viewContact(String conId);
	List<CrmContact> listSomeFieldsOfContact();
}
