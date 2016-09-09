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
import com.balancika.crm.services.CrmNoteService;

@RestController
@RequestMapping("/api/note")
public class NoteController {
	
	@Autowired
	private CrmNoteService noteService;
	
	@RequestMapping(value="/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listNotes(){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmNote> arrNote = noteService.listNotes();
		if(arrNote != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrNote);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}

	@RequestMapping(value="/list/{noteId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findNoteById(@PathVariable("noteId") String noteId){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmNote note = noteService.findNoteById(noteId);
		if(note != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", note);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/lead/{leadId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listNoteRelatedToLead(@PathVariable("leadId") String leadId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmNote> note = noteService.listNoteRelatedToLead(leadId);
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
	
	@RequestMapping(value="/list/opp/{opId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listNoteRelatedToOpportuntity(@PathVariable("opId") String opId){
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmNote> note = noteService.listNotesRelatedToOpportunity(opId);
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
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateNote(@RequestBody CrmNote note){
		Map<String, Object> map = new HashMap<String, Object>();
		if(noteService.updateNote(note) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/remove/{noteId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteNote(@PathVariable("noteId") String noteId){
		Map<String, Object> map = new HashMap<String, Object>();
		if(noteService.deleteNote(noteId) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}	
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
