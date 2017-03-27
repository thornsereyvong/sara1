package com.balancika.crm.utilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppUtilities {

	public static Object aliasToMaps(ResultSet rs){
		try {
			ArrayList<Map<String, Object>> arr = new ArrayList<>();
			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
			Map<String, Object> map = null; 
			while(rs.next()){
				 map = new HashMap<>();
				 for (int i = 1; i <= meta.getColumnCount(); i++) {
	                String key = meta.getColumnLabel(i);
	                Object value = rs.getObject(i);
	                map.put(key, value);
	            }
				arr.add(map);
			}
			return arr;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public static Object aliasToSingleMap(ResultSet rs){
		try {
			ResultSetMetaData meta = (ResultSetMetaData) rs.getMetaData();
			Map<String, Object> map = null; 
			while(rs.next()){
				 map = new HashMap<>();
				 for (int i = 1; i <= meta.getColumnCount(); i++) {
	                String key = meta.getColumnLabel(i);
	                Object value = rs.getObject(i);
	                map.put(key, value);
	            }
			}
			return map;			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
}
