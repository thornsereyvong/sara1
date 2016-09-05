package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmMeetingStatus;

public interface CrmMeetingStatusDao {
	
	boolean insertMeetingStatus(CrmMeetingStatus status);
	boolean updateMeetingStatus(CrmMeetingStatus status);
	String deleteMeetingStatus(int statusId);
	CrmMeetingStatus findMeetingStatusById(int statusId);
	List<CrmMeetingStatus> listMeetingStatus();
}
