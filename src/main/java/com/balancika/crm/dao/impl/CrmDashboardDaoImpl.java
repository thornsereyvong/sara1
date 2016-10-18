package com.balancika.crm.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.balancika.crm.dao.CrmDashboardDao;

@Repository
public class CrmDashboardDaoImpl implements CrmDashboardDao{

	@Autowired
	private SessionFactory sessionFactory;
	
	@SuppressWarnings("unchecked")
	@Override
	public Object viewDashboard(String username) {
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("CALL loadCrmDashboardForSpecificUser(:username)");
		query.setParameter("username", username);
		query.setResultTransformer(Criteria.ALIAS_TO_ENTITY_MAP);
		List<Map<String, Object>> maps = query.list();
		Map<String, Object> objMap = new HashMap<String, Object>();
		
		List<Map<String, Object>> meetingList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> taskList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> callList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> noteList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> eventList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> locationList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> leadList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> campaignList = new ArrayList<Map<String,Object>>();
		List<Map<String, Object>> caseList = new ArrayList<Map<String,Object>>();
		for(Map<String, Object> map : maps){
			//System.err.println(map.get("KEY")+" "+map.get("VAL"));
			switch (map.get("KEY").toString()) {
				case "MEETING":
					meetingList.add(generateMeetingMap(map.get("VAL").toString()));
					break;
				case "TASK":
					taskList.add(generateTaskMap(map.get("VAL").toString()));
					break;
				case "CALL":
					callList.add(generateCallMap(map.get("VAL").toString()));
					break;
				case "NOTE":
					noteList.add(generateNoteMap(map.get("VAL").toString()));
					break;
				case "EVENT":
					eventList.add(generateEventMap(map.get("VAL").toString()));
					break;
				case "LOCATION":
					locationList.add(generateLocationMap(map.get("VAL").toString()));
					break;
				case "LEAD":
					leadList.add(generateLeadMap(map.get("VAL").toString()));
					break;
				case "CAMPAIGN":
					campaignList.add(generateCampaignMap(map.get("VAL").toString()));
					break;
				case "CASE":
					caseList.add(generateCaseMap(map.get("VAL").toString()));
					break;
				default:
					break;
			}
			
		}
		objMap.put("MEETINGS", meetingList);
		objMap.put("TASKS", taskList);
		objMap.put("CALLS", callList);
		objMap.put("NOTES", noteList);
		objMap.put("EVENTS", eventList);
		objMap.put("LOCATIONS", locationList);
		objMap.put("LEADS", leadList);
		objMap.put("CAMPAIGNS", campaignList);
		objMap.put("CASES", caseList);
		return objMap;
	}
	
	private Map<String, Object> generateMeetingMap(String meetings){
		String[] str = meetings.split(" ,");
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			map.put("meetingId", str[0]);
			map.put("meetingSubject", str[1]);
			map.put("relatedRelatedTo", str[2]);
			map.put("meetingLocation", str[3]);
			map.put("meetingStartDate", str[4]);
			map.put("meetingEndDate", str[5]);
			map.put("meetingStatus", str[6]);
			map.put("meetingRelatedToType", str[7]);
			map.put("meetingRelatedToId", str[8]);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return map;
	}
	
	private Map<String, Object> generateTaskMap(String task){
		String[] str = task.split(" ,");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("taskId", str[0]);
		map.put("taskSubject", str[1]);
		map.put("taskRelatedTo", str[2]);
		map.put("taskPriority", str[3]);
		map.put("taskStatus", str[4]);
		map.put("taskStartDate", str[5]);
		map.put("taskDueDate", str[6]);
		map.put("taskRelatedToType", str[7]);
		map.put("taskRelatedToId", str[8]);
		return map;
	}
	
	private Map<String, Object> generateCallMap(String call){
		String[] str = call.split(" ,");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("callId", str[0]);
		map.put("callSubject", str[1]);
		map.put("callRelatedTo", str[2]);
		map.put("callStartDate", str[3]);
		map.put("callDuration", str[4]);
		map.put("callStatus", str[5]);
		map.put("callRelatedToType", str[6]);
		map.put("callRelatedToId", str[7]);
		return map;
	}
	
	private Map<String, Object> generateNoteMap(String note){
		String[] str = note.split(" ,");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("noteId", str[0]);
		map.put("noteSubject", str[1]);
		map.put("noteRelatedTo", str[2]);
		map.put("noteCreatedDate", str[3]);
		map.put("noteRelatedToType", str[4]);
		map.put("noteRelatedToId", str[5]);
		return map;
	}
	
	private Map<String, Object> generateEventMap(String event){
		String[] str = event.split(" ,");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("eventId", str[0]);
		map.put("eventName", str[1]);
		map.put("eventRelatedTo", str[2]);
		map.put("eventRelatedToType", str[3]);
		map.put("eventRelatedToId", str[4]);
		map.put("eventLocation", str[5]);
		map.put("eventStartDate", str[6]);
		map.put("eventEndDate", str[7]);
		map.put("eventBudget", str[8]);
		return map;
	}
	
	private Map<String, Object> generateLocationMap(String location){
		String[] str = location.split(" ,");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("locationId", str[0]);
		map.put("locationName", str[1]);
		map.put("locationCreatedDate", str[2]);
		return map;
	}
	
	private Map<String, Object> generateLeadMap(String lead){
		String[] str = lead.split(" ,");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("leadId", str[0]);
		map.put("leadName", str[1]);
		map.put("leadStatus", str[2]);
		map.put("leadCompany", str[3]);
		map.put("leadEmail", str[4]);
		map.put("leadPhone", str[5]);
		return map;
	}
	
	private Map<String, Object> generateCampaignMap(String campaign){
		String[] str = campaign.split(" ,");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("campId", str[0]);
		map.put("campName", str[1]);
		map.put("campStartDate", str[2]);
		map.put("campEndDate", str[3]);
		map.put("campStatus", str[4]);
		map.put("campType", str[5]);
		map.put("campBudget", str[6]);
		return map;
	}
	
	private Map<String, Object> generateCaseMap(String cases){
		String[] str = cases.split(" ,");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("caseId", str[0]);
		map.put("caseSubject", str[1]);
		map.put("caseStatus", str[2]);
		map.put("caseType", str[3]);
		map.put("casePriority", str[4]);
		map.put("custId", str[5]);
		map.put("caseCustomer", str[6]);
		map.put("conId", str[7]);
		map.put("caseContact", str[8]);
		return map;
	}
}
