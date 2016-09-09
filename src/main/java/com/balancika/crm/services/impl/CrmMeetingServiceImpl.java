package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmMeetingDao;
import com.balancika.crm.model.CrmMeeting;
import com.balancika.crm.services.CrmMeetingService;

@Service
@Transactional
public class CrmMeetingServiceImpl implements CrmMeetingService{

	@Autowired
	private CrmMeetingDao meetingDao;

	@Override
	public List<CrmMeeting> listMeetings() {
		return meetingDao.listMeetings();
	}

	@Override
	public boolean insertMeeting(CrmMeeting meeting) {
		return meetingDao.insertMeeting(meeting);
	}

	@Override
	public boolean updateMeeting(CrmMeeting meeting) {
		return meetingDao.updateMeeting(meeting);
	}

	@Override
	public boolean deleteMeeting(String meetingId) {
		return meetingDao.deleteMeeting(meetingId);
	}

	@Override
	public Object findMeetingById(String meetingId) {
		return meetingDao.findMeetingById(meetingId);
	}

	@Override
	public CrmMeeting findMeetingDetailsById(String meetingId) {
		return meetingDao.findMeetingDetailsById(meetingId);
	}

	@Override
	public List<CrmMeeting> listTasksRelatedToLead(String leadId) {
		return meetingDao.listTasksRelatedToLead(leadId);
	}

	@Override
	public List<CrmMeeting> listTasksRelatedToOpportunity(String opId) {
		return meetingDao.listTasksRelatedToOpportunity(opId);
	}
	
	
}
