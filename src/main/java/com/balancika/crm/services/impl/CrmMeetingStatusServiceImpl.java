package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmMeetingStatusDao;
import com.balancika.crm.model.CrmMeetingStatus;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMeetingStatusService;

@Service
@Transactional
public class CrmMeetingStatusServiceImpl implements CrmMeetingStatusService{

	@Autowired
	private CrmMeetingStatusDao statusDao;

	@Override
	public boolean insertMeetingStatus(CrmMeetingStatus status) {
		return statusDao.insertMeetingStatus(status);
	}

	@Override
	public boolean updateMeetingStatus(CrmMeetingStatus status) {
		return statusDao.updateMeetingStatus(status);
	}

	@Override
	public String deleteMeetingStatus(CrmMeetingStatus status) {
		return statusDao.deleteMeetingStatus(status);
	}

	@Override
	public CrmMeetingStatus findMeetingStatusById(int statusId, MeDataSource dataSource) {
		return statusDao.findMeetingStatusById(statusId, dataSource);
	}

	@Override
	public List<CrmMeetingStatus> listMeetingStatus(MeDataSource dataSource) {
		return statusDao.listMeetingStatus(dataSource);
	}
}
