package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmNoteDao;
import com.balancika.crm.model.CrmNote;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmNoteService;

@Service
@Transactional
public class CrmNoteServiceImpl implements CrmNoteService{

	@Autowired
	private CrmNoteDao noteDao;
	
	@Override
	public boolean insertNote(CrmNote note) {
		return noteDao.insertNote(note);
	}

	@Override
	public boolean updateNote(CrmNote note) {
		return noteDao.updateNote(note);
	}

	@Override
	public boolean deleteNote(CrmNote note) {
		return noteDao.deleteNote(note);
	}

	@Override
	public List<CrmNote> listNotes(MeDataSource dataSource) {
		return noteDao.listNotes(dataSource);
	}

	@Override
	public CrmNote findNoteById(String noteId, MeDataSource dataSource) {
		return noteDao.findNoteById(noteId, dataSource);
	}

	@Override
	public List<CrmNote> listNoteRelatedToLead(String leadId, MeDataSource dataSource) {
		return noteDao.listNoteRelatedToLead(leadId, dataSource);
	}

	@Override
	public List<CrmNote> listNotesRelatedToOpportunity(String opId, MeDataSource dataSource) {
		return noteDao.listNotesRelatedToOpportunity(opId, dataSource);
	}

	@Override
	public List<CrmNote> listNoteRelatedToEachModule(String moduleId, MeDataSource dataSource) {
		return noteDao.listNoteRelatedToEachModule(moduleId, dataSource);
	}

}
