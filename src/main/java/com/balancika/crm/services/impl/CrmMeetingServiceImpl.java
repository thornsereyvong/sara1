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
		try{
			return meetingDao.listMeetings();
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public boolean insertMeeting(CrmMeeting meeting) {
		try{
			return meetingDao.insertMeeting(meeting);
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean updateMeeting(CrmMeeting meeting) {
		try{
			return meetingDao.updateMeeting(meeting);
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public boolean deleteMeeting(String meetingId) {
		try{
			return meetingDao.deleteMeeting(meetingId);
		}catch(Exception e){
			return false;
		}
	}

	@Override
	public Object findMeetingById(String meetingId) {
		try{
			return meetingDao.findMeetingById(meetingId);
		}catch(Exception e){
			return null;
		}
	}

	@Override
	public CrmMeeting findMeetingDetailsById(String meetingId) {
		try{
			return meetingDao.findMeetingDetailsById(meetingId);
		}catch(Exception e){
			return null;
		}
	}
	
	
}
