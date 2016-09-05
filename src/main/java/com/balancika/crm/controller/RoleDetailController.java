package com.balancika.crm.controller;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmRoleDetail;
import com.balancika.crm.services.CrmRoleDetailService;

@RestController
@RequestMapping("/api/role_detail")
public class RoleDetailController {

	@Autowired
	private CrmRoleDetailService roleDetailService;
	
	
	@RequestMapping(value = "/list", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> listRoleDetails(){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		List<CrmRoleDetail> roleDetails = roleDetailService.listRoleDetails();
		if(roleDetails != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", roleDetails);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/list/{roleDetailId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findRoleDetailById(@PathVariable("roleDetailId") int roleDetailId){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		CrmRoleDetail roleDetails = roleDetailService.findRoleDetailById(roleDetailId);
		if(roleDetails != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", roleDetails);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/list/user/{username}/{moduleId}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<Map<String, Object>> findRoleDetailsByUsername(@PathVariable("username") String username, @PathVariable("moduleId") String moduleId){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		Object roleDetails = roleDetailService.findRoleDetailsByUsername(username,moduleId);
		if(roleDetails != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", roleDetails);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json")
	public ResponseEntity<Map<String, Object>> addRoleDetail(@RequestBody CrmRoleDetail roleDetail){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if(roleDetailService.insertRoleDetail(roleDetail) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/edit", method = RequestMethod.PUT, produces = "application/json")
	public ResponseEntity<Map<String, Object>> updateRoleDetail(@RequestBody CrmRoleDetail roleDetail){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if(roleDetailService.updateRoleDetail(roleDetail) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value = "/remove/{roleDetailId}", method = RequestMethod.DELETE, produces = "application/json")
	public ResponseEntity<Map<String, Object>> deleteRoleDetail(@PathVariable("roleDetailId") int roleDetailId){
		Map<String, Object> map = new ConcurrentHashMap<String, Object>();
		if(roleDetailService.deleteRoleDetail(roleDetailId) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
