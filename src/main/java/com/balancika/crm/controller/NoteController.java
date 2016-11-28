package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmNote;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmNoteService;
import com.balancika.crm.services.CrmUserActivityService;

@RestController
@RequestMapping("/api/note")
public class NoteController {
	
	@Autowired
	private CrmNoteService noteService;
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmMessageService messageService;
	
	@Autowired
	private CrmUserActivity activity;
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listNotes(@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmNote> arrNote = noteService.listNotes(dataSource);
		if(arrNote != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrNote);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}

	@RequestMapping(value="/list/{noteId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findNoteById(@PathVariable("noteId") String noteId,@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmNote note = noteService.findNoteById(noteId, dataSource);
		if(note != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", note);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/lead/{leadId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listNoteRelatedToLead(@PathVariable("leadId") String leadId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmNote> note = noteService.listNoteRelatedToLead(leadId, dataSource);
		if(note != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("NOTES", note);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("DATA", note);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/opp/{opId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listNoteRelatedToOpportuntity(@PathVariable("opId") String opId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmNote> note = noteService.listNotesRelatedToOpportunity(opId, dataSource);
		if(note != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("NOTES", note);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("DATA", note);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/module/{moduleId}", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listNoteRelatedToModule(@PathVariable("moduleId") String moduleId, @RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmNote> note = noteService.listNoteRelatedToEachModule(moduleId, dataSource);
		if(note != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("NOTES", note);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("DATA", note);
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addNote(@RequestBody CrmNote note){
		Map<String, Object> map = new HashMap<String, Object>();
		if(noteService.insertNote(note) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			
			map.put("MSG", messageService.getMessage("1000", "note", note.getNoteId(), note.getMeDataSource()));			
			activityService.addUserActivity(activity.getActivity(note.getMeDataSource(), "Create", "Note", note.getNoteId()));
			
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "note", "", note.getMeDataSource()));
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateNote(@RequestBody CrmNote note){
		Map<String, Object> map = new HashMap<String, Object>();
		if(noteService.updateNote(note) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1001", "note", note.getNoteId(), note.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(note.getMeDataSource(), "Update", "Note", note.getNoteId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		
		map.put("MSG", messageService.getMessage("1004", "call", note.getNoteId(), note.getMeDataSource()));
		
		
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteNote(@RequestBody CrmNote note){
		Map<String, Object> map = new HashMap<String, Object>();
		if(noteService.deleteNote(note) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			
			map.put("MSG", messageService.getMessage("1002", "note", note.getNoteId(), note.getMeDataSource()));
			activityService.addUserActivity(activity.getActivity(note.getMeDataSource(), "Delete", "Note", note.getNoteId()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1005", "note", note.getNoteId(), note.getMeDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
