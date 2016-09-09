package com.balancika.crm.services.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.balancika.crm.dao.CrmNoteDao;
import com.balancika.crm.model.CrmNote;
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
	public boolean deleteNote(String noteId) {
		return noteDao.deleteNote(noteId);
	}

	@Override
	public List<CrmNote> listNotes() {
		return noteDao.listNotes();
	}

	@Override
	public CrmNote findNoteById(String noteId) {
		return noteDao.findNoteById(noteId);
	}

	@Override
	public List<CrmNote> listNoteRelatedToLead(String leadId) {
		return noteDao.listNoteRelatedToLead(leadId);
	}

	@Override
	public List<CrmNote> listNotesRelatedToOpportunity(String opId) {
		return noteDao.listNotesRelatedToOpportunity(opId);
	}

}
