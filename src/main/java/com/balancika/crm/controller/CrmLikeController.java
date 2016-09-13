package com.balancika.crm.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.balancika.crm.model.CrmLike;
import com.balancika.crm.services.CrmLikeService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/api/like")
public class CrmLikeController {

	@Autowired
	private CrmLikeService likeService;
	
	@RequestMapping(value = "/like/{collapId}", method = RequestMethod.GET)
	public ResponseEntity<Map<String, Object>> likeAmount(@PathVariable("collapId") int collapId){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("like", likeService.countLike(collapId));
		return new ResponseEntity<Map<String,Object>>(map , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/like", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> likeOperation(@RequestBody String json){
		
		ObjectMapper mapper = new ObjectMapper();
		Map<String, String> jsonMap = new HashMap<String, String>();
		try {
			jsonMap = mapper.readValue(json, new TypeReference<Map<String, String>>(){});
		} catch (IOException e) {
			e.printStackTrace();
		}
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(jsonMap.get("likeStatus").equals(true)){
			CrmLike like = new CrmLike();
			like.setCollapId(Integer.parseInt(jsonMap.get("collapId")));
			like.setUsername(jsonMap.get("username").toString());
			if(likeService.insertLike(like) == true){
				map.put("MESSAGE", "LIKED");
			}else{
				map.put("MESSAGE", "FAILED");
			}
		}else {
			if(likeService.deleteLike(jsonMap.get("username").toString()) == true){
				map.put("MESSAGE", "UNLIKED");
			}else{
				map.put("MESSAGE", "FAILED");
			}
		}
		return new ResponseEntity<Map<String,Object>>(map , HttpStatus.OK);
	}
}
