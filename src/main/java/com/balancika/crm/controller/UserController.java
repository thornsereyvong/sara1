package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private CrmUserService userService; 
	
	@RequestMapping(value="/list_all", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> listAllUsers(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmUser> arrUser = userService.listAllUsers(dataSource);
		if(arrUser != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrUser);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/user_tags", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> listAllUsernameAndId(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> arrUser = userService.listAllUsernameAndId(dataSource);
		if(arrUser != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrUser);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", arrUser);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> findUserByUsername(@RequestBody CrmUser users){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmUser user = userService.findUserByUsername(users);
		if(user != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", user);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/id", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> findUserById(@RequestBody CrmUser users){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmUser user = userService.findUserById(users);
		if(user != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", user);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/list/subordinate", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> listSubordinateUsers(@RequestBody CrmUser user){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmUser> arrUser = userService.listSubordinateUserByUsername(user);
		if(arrUser != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrUser);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/login/web", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> webLogin(@RequestBody CrmUser users){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmUser user = userService.webLogin(users);
		if(user != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", user);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> addNewUser(@RequestBody CrmUser user){
	
		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.isInserted(user) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody CrmUser user){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.isUpdated(user) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody CrmUser user){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.isDeleted(user) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
