package com.balancika.crm.dao;

import java.util.List;

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
	CrmContact viewContact(String conId);
}
