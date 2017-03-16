package com.balancika.crm.services.impl;

import java.util.List;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmMeetingDao;
import com.balancika.crm.model.CrmMeeting;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMeetingService;

@Service
@Transactional
public class CrmMeetingServiceImpl implements CrmMeetingService{

	@Autowired
	private CrmMeetingDao meetingDao;

	@Override
	public List<CrmMeeting> listMeetings(MeDataSource dataSource) {
		return meetingDao.listMeetings(dataSource);
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
	public boolean deleteMeeting(CrmMeeting meeting) {
		return meetingDao.deleteMeeting(meeting);
	}

	@Override
	public Object findMeetingById(String meetingId, MeDataSource dataSource) {
		return meetingDao.findMeetingById(meetingId, dataSource);
	}

	@Override
	public CrmMeeting findMeetingDetailsById(String meetingId , MeDataSource dataSource) {
		return meetingDao.findMeetingDetailsById(meetingId, dataSource);
	}

	@Override
	public List<CrmMeeting> listMeetingsRelatedToLead(String leadId, MeDataSource dataSource) {
		return meetingDao.listMeetingsRelatedToLead(leadId, dataSource);
	}

	@Override
	public List<CrmMeeting> listMeetingsRelatedToOpportunity(String opId, MeDataSource dataSource) {
		return meetingDao.listMeetingsRelatedToOpportunity(opId, dataSource);
	}

	@Override
	public List<CrmMeeting> listMeetingsRelatedToModule(String moduleId, MeDataSource dataSource) {
		return meetingDao.listMeetingsRelatedToModule(moduleId, dataSource);
	}

	@Override
	public Map<String, Object> listMeetingsForMobile(int rowNum, int pageNum, MeDataSource dataSource) {
		return meetingDao.listMeetingsForMobile(rowNum, pageNum, dataSource);
	}
	
	
}
