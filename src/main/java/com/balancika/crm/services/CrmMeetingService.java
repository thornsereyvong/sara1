package com.balancika.crm.services;

import java.util.List;
import java.util.Map;

import com.balancika.crm.model.CrmMeeting;
import com.balancika.crm.model.CrmMeetingCheckin;
import com.balancika.crm.model.MeDataSource;

public interface CrmMeetingService {
	List<CrmMeeting> listMeetings(MeDataSource dataSource);
	boolean insertMeeting(CrmMeeting meeting);
	boolean updateMeeting(CrmMeeting meeting);
	boolean deleteMeeting(CrmMeeting meeting);
	Object findMeetingById(String meetingId, MeDataSource dataSource);
	CrmMeeting findMeetingDetailsById(String meetingId, MeDataSource dataSource);
	List<CrmMeeting> listMeetingsRelatedToLead(String leadId, MeDataSource dataSource);
	List<CrmMeeting> listMeetingsRelatedToOpportunity(String opId, MeDataSource dataSource);
	List<CrmMeeting> listMeetingsRelatedToModule(String moduleId, MeDataSource dataSource);
	Map<String, Object> listMeetingsForMobile(int rowNum, int pageNum, MeDataSource dataSource);
	Map<String, Object> meetingCheckIn(CrmMeetingCheckin checkin);
}
