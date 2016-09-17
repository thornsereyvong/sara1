package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmMeeting;

public interface CrmMeetingDao {

	List<CrmMeeting> listMeetings();
	boolean insertMeeting(CrmMeeting meeting);
	boolean updateMeeting(CrmMeeting meeting);
	boolean deleteMeeting(String meetingId);
	Object findMeetingById(String meetingId);
	CrmMeeting findMeetingDetailsById(String meetingId);
	List<CrmMeeting> listMeetingsRelatedToLead(String leadId);
	List<CrmMeeting> listMeetingsRelatedToOpportunity(String opId);
	List<CrmMeeting> listMeetingsRelatedToModule(String moduleId);
}
