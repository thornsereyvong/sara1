package com.balancika.crm.utilities;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AppUtilities {

	public Map<String, Object> aliasToMap(ResultSet rs, String mapKey){
		ResultSetMetaData md;
		Map<String, Object> map = new HashMap<String, Object>();
		try {
			md = rs.getMetaData();
			int columns = md.getColumnCount();
				ArrayList<Object> list = new ArrayList<Object>();
				while (rs.next()){
					HashMap<String, Object> row = new HashMap<String, Object>(columns);
					for(int i=1; i<=columns; ++i){           
						row.put(md.getColumnLabel(i),rs.getObject(i));
					}
			    list.add(row);
			  }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return map;
	}
}
