package com.balancika.crm.dao;

import java.util.List;

import com.balancika.crm.model.CrmNote;

public interface CrmNoteDao{
	boolean insertNote(CrmNote note);
	boolean updateNote(CrmNote note);
	boolean deleteNote(String noteId);
	List<CrmNote> listNotes();
	CrmNote findNoteById(String noteId);
	List<CrmNote> listNoteRelatedToLead(String leadId);
	List<CrmNote> listNotesRelatedToOpportunity(String opId);
}
