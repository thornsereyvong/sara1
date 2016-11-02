package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmMeetingStatus;
import com.balancika.crm.model.MeDataSource;

public interface CrmMeetingStatusService {
	boolean insertMeetingStatus(CrmMeetingStatus status);
	boolean updateMeetingStatus(CrmMeetingStatus status);
	String deleteMeetingStatus(CrmMeetingStatus status);
	CrmMeetingStatus findMeetingStatusById(int statusId, MeDataSource dataSource);
	List<CrmMeetingStatus> listMeetingStatus(MeDataSource dataSource);
}
