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
		try {
			return noteDao.insertNote(note);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean updateNote(CrmNote note) {
		try {
			return noteDao.updateNote(note);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public boolean deleteNote(String noteId) {
		try {
			return noteDao.deleteNote(noteId);
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public List<CrmNote> listNotes() {
		try {
			return noteDao.listNotes();
		} catch (Exception e) {
			return null;
		}
	}

	@Override
	public CrmNote findNoteById(String noteId) {
		try {
			return noteDao.findNoteById(noteId);
		} catch (Exception e) {
			return null;
		}
	}

}
