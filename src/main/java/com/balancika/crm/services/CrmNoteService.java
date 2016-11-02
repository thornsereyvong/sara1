package com.balancika.crm.services;

import java.util.List;

import com.balancika.crm.model.CrmNote;
import com.balancika.crm.model.MeDataSource;

public interface CrmNoteService {

	boolean insertNote(CrmNote note);
	boolean updateNote(CrmNote note);
	boolean deleteNote(CrmNote note);
	List<CrmNote> listNotes(MeDataSource dataSource);
	CrmNote findNoteById(String noteId, MeDataSource dataSource);
	List<CrmNote> listNoteRelatedToLead(String leadId, MeDataSource dataSource);
	List<CrmNote> listNotesRelatedToOpportunity(String opId, MeDataSource dataSource);
	List<CrmNote> listNoteRelatedToEachModule(String moduleId, MeDataSource dataSource);
}
