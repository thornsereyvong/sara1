package com.balancika.crm.controller;

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
import com.balancika.crm.model.MeDataSource;
import com.balancika.crm.services.CrmLikeService;

@RestController
@RequestMapping("/api/like")
public class CrmLikeController {

	@Autowired
	private CrmLikeService likeService;
	
	@RequestMapping(value = "/like/{collapId}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> likeAmount(@PathVariable("collapId") int collapId, @RequestBody MeDataSource dataSource)	{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("like", likeService.countLike(collapId, dataSource));
		return new ResponseEntity<Map<String,Object>>(map , HttpStatus.OK);
	}
	
	@RequestMapping(value = "/like/{likeStatus}/{collapId}/{username}", method = RequestMethod.POST)
	public ResponseEntity<Map<String, Object>> likeOperation(@RequestBody MeDataSource dataSource, @PathVariable("likeStatus") String likeStatus, @PathVariable("collapId") int collapId, @PathVariable("username") String username){
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(likeStatus.equalsIgnoreCase("true")){
			CrmLike like = new CrmLike();
			like.setCollapId(collapId);
			like.setUsername(username);
			like.setMeDataSource(dataSource);
			if(likeService.insertLike(like) == true){
				map.put("MESSAGE", "LIKED");
			}else{
				map.put("MESSAGE", "FAILED");
			}
		}else {
			if(likeService.deleteLike(collapId,dataSource) == true){
				map.put("MESSAGE", "UNLIKED");
			}else{
				map.put("MESSAGE", "FAILED");
			}
		}
		return new ResponseEntity<Map<String,Object>>(map , HttpStatus.OK);
	}
}
