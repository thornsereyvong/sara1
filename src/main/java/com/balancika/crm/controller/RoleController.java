package com.balancika.crm.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmRole;
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmRoleService;

@RestController
@RequestMapping("/api/role")
public class RoleController {

	@Autowired
	@Qualifier("CrmRoleService")
	private CrmRoleService roleService;
	
	@RequestMapping(value="/list", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> listAllRoles(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<CrmRole> arrRole = roleService.listAllRoles(dataSource);
		if(arrRole != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", arrRole);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/{roleId}", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> findRoleById(@PathVariable("roleId") String roleId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmRole roles = roleService.findRoleById(roleId, dataSource);
		if(roles != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", roles);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/role_details/{roleId}", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> findRoleDetailsByRoleId(@PathVariable("roleId") String roleId, @RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		List<Object> roles = roleService.findRoleDetailsByRoleId(roleId, dataSource);
		if(roles != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", roles);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/list/role_by_user", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> findRoleByUsername(@RequestBody MeDataSource dataSource){
		
		Map<String, Object> map = new HashMap<String, Object>();
		CrmRole roles = roleService.findRoleByUsername(dataSource.getUserid(), dataSource);
		if(roles != null){
			map.put("MESSAGE", "SUCCESS");
			map.put("STATUS", HttpStatus.OK.value());
			map.put("DATA", roles);
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.NOT_FOUND.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/add", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> addNewRole(@RequestBody CrmRole role){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(roleService.isInsertedRole(role) == true){
			map.put("MESSAGE", "INSERTED");
			map.put("STATUS", HttpStatus.CREATED.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.CREATED);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/edit", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> updateRole(@RequestBody CrmRole role){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(roleService.isUpdatedRole(role) == true){
			map.put("MESSAGE", "UPDATED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
	
	@RequestMapping(value="/remove/", method = RequestMethod.POST, produces="application/json")
	public ResponseEntity<Map<String, Object>> deleteRole(@RequestBody CrmRole role){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(roleService.isDeletedRole(role) == true){
			map.put("MESSAGE", "DELETED");
			map.put("STATUS", HttpStatus.OK.value());
			return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
		}
		
		map.put("MESSAGE", "FAILED");
		map.put("STATUS", HttpStatus.INTERNAL_SERVER_ERROR.value());
		return new ResponseEntity<Map<String,Object>>(map, HttpStatus.OK);
	}
}
