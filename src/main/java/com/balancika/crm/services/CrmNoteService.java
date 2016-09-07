package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmNote;

public interface CrmNoteService {

	boolean insertNote(CrmNote note);
	boolean updateNote(CrmNote note);
	boolean deleteNote(String noteId);
	List<CrmNote> listNotes();
	CrmNote findNoteById(String noteId);
	List<CrmNote> listNoteRelatedToLead(String leadId);
}
