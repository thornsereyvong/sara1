package com.balancika.crm.utilities;

import java.sql.Connection;
import java.sql.SQLException;

import org.springframework.jdbc.datasource.DriverManagerDataSource;

import com.balancika.crm.model.MeDataSource;

public class DBConnection {
	
	public static Connection getConnection(MeDataSource dataSource){
		try {
			DriverManagerDataSource source = new DriverManagerDataSource();
			source.setDriverClassName("com.mysql.jdbc.Driver");
			source.setUrl("jdbc:mysql://"+dataSource.getIp()+":"+dataSource.getPort()+"/"+dataSource.getDb()+"?useUnicode=true&characterEncoding=UTF-8");
			source.setUsername(dataSource.getUn());
			source.setPassword(dataSource.getPw());
			return source.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

}
