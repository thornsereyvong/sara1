package com.balancika.crm.configuration;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.balancika.crm.model.MeDataSource;

public class HibernateSessionFactory {
	
	@Autowired
	private static MeDataSource meDataSource;

	public static SessionFactory getSessionFactory(){
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://"+meDataSource.getIp()+":"+meDataSource.getPort()+"/"+meDataSource.getDb()+"?useUnicode=true&characterEncoding=UTF-8");
		basicDataSource.setUsername(meDataSource.getUn());
		basicDataSource.setPassword(meDataSource.getPw());
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(basicDataSource);
		sessionBuilder.scanPackages("com.balancika.crm.model");
		sessionBuilder.addProperties(getHibernateProperties());
		return sessionBuilder.buildSessionFactory();
	}
	
	private static Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    properties.put("hibernate.format_sql", "true");
	    properties.put("hibernate.connection.autocommit", "true");
	    return properties;
	}
}
