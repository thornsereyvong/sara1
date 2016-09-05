package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmMeetingStatus;

public interface CrmMeetingStatusService {
	boolean insertMeetingStatus(CrmMeetingStatus status);
	boolean updateMeetingStatus(CrmMeetingStatus status);
	String deleteMeetingStatus(int statusId);
	CrmMeetingStatus findMeetingStatusById(int statusId);
	List<CrmMeetingStatus> listMeetingStatus();
}
