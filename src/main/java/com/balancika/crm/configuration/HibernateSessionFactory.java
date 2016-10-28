package com.balancika.crm.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;

import com.balancika.crm.model.MeDataSource;

public class HibernateSessionFactory {
	
	public static SessionFactory getSessionFactory(MeDataSource meDataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource(meDataSource));
		sessionBuilder.scanPackages("com.balancika.crm.model");
		sessionBuilder.addProperties(getHibernateProperties());
		return sessionBuilder.buildSessionFactory();
	}
	
	
	public static DataSource dataSource(MeDataSource meDataSource){
		BasicDataSource basicDataSource = new BasicDataSource();
		basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		basicDataSource.setUrl("jdbc:mysql://"+meDataSource.getIp()+":"+meDataSource.getPort()+"/"+meDataSource.getDb()+"?useUnicode=true&characterEncoding=UTF-8");
		basicDataSource.setUsername(meDataSource.getUn());
		basicDataSource.setPassword(meDataSource.getPw());
		return basicDataSource;
	}
	
	private static Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    properties.put("hibernate.format_sql", "true");
	    properties.put("hibernate.connection.autocommit", "true");
	    return properties;
	}
	
	public static void shutdown(MeDataSource meDataSource){
		getSessionFactory(meDataSource).close();
	}
}
