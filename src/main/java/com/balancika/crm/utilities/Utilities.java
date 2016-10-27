package com.balancika.crm.utilities;

import org.springframework.beans.factory.annotation.Autowired;

import com.balancika.crm.model.MeDataSource;

public class Utilities {

	@Autowired
	private static  MeDataSource meDataSource;
	
	public static void setMeDataSource(String ip, String db, String port, String pw, String un) {
		meDataSource.setDb(db);
		meDataSource.setIp(ip);
		meDataSource.setPort(port);
		meDataSource.setPw(pw);
		meDataSource.setUn(un);
		System.out.println(db);
	}
}
