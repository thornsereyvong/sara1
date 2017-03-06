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

import com.balancika.crm.model.CrmUser;
import com.balancika.crm.model.CrmUserActivity;
import com.balancika.crm.model.CrmUserLogin;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmMessageService;
import com.balancika.crm.services.CrmRoleService;
import com.balancika.crm.services.CrmUserActivityService;
import com.balancika.crm.services.CrmUserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private CrmUserService userService; 
	
	@Autowired
	private CrmRoleService roleService;
	
	@Autowired
	private CrmMessageService messageService;
	
	
	@Autowired
	private CrmUserActivityService activityService;
	
	@Autowired
	private CrmUserActivity activity;
	
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
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	@RequestMapping(value="/startup/list_all", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> listStartup(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmUser> arrUser = userService.listAllUsers(dataSource);
		List<Object> roleList = roleService.findRoleMaster(dataSource);
		
		if(arrUser != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrUser);
			map.put("ROLE", roleList);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		map.put("DATA", null);
		map.put("ROLE", roleList);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
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
	
	@RequestMapping(value="/list/{username}", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> findUserByUsername(@PathVariable("username") String username,@RequestBody MeDataSource dataSource){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmUser user = userService.findUserByUsername(username, dataSource);
		if(user != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", user);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/id/{userID}", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> findUserById(@PathVariable("userID") String userID, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmUser user = userService.findUserById(userID, dataSource);
		if(user != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", user);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/subordinate/{username}", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> listSubordinateUsers(@RequestBody MeDataSource dataSource, @PathVariable("username") String username){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmUser> arrUser = userService.listSubordinateUserByUsername(username, dataSource);
		if(arrUser != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrUser);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/login/web", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> webLogin(@RequestBody CrmUserLogin users){
		Map<String, Object> map = new HashMap<String, Object>();
		CrmUserLogin user = userService.webLogin(users);
		if(user != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", user);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	/*Mobile Login URL*/
	@RequestMapping(value="/login/mobile", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> mobileLogin(@RequestBody CrmUserLogin user){
		Map<String, Object> map = userService.mobileLogin(user);
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	/*End Mobile Login URL*/
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> addNewUser(@RequestBody CrmUser user){
	
		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.isInserted(user) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			
			activityService.addUserActivity(activity.getActivity(user.getDataSource(), "Create", "User", user.getUserID()));
			map.put("MSG", messageService.getMessage("1000", "user", user.getUserID(), user.getDataSource()));
			
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1003", "user", "", user.getDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> updateUser(@RequestBody CrmUser user){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.isUpdated(user) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1001", "user", user.getUserID(), user.getDataSource()));
			activityService.addUserActivity(activity.getActivity(user.getDataSource(), "Update", "User", user.getUserID()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "user", user.getUserID(), user.getDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> deleteUser(@RequestBody CrmUser user){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(userService.isDeleted(user) == true){
			activityService.addUserActivity(activity.getActivity(user.getDataSource(), "Delete", "User", user.getUserID()));
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("MSG", messageService.getMessage("1002", "user", user.getUserID(), user.getDataSource()));
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		map.put("MSG", messageService.getMessage("1004", "user", user.getUserID(), user.getDataSource()));
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
