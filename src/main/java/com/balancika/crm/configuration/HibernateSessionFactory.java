package com.balancika.crm.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.balancika.crm.model.MeDataSource;

@EnableTransactionManagement
public class HibernateSessionFactory {
	
	public SessionFactory getSessionFactory(MeDataSource meDataSource){
		LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource(meDataSource));
		sessionBuilder.scanPackages("com.balancika.crm.model");
		sessionBuilder.addProperties(getHibernateProperties());
		return sessionBuilder.buildSessionFactory();
	}
	
	
	public DataSource dataSource(MeDataSource meDataSource){
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		dataSource.setUrl("jdbc:mysql://"+meDataSource.getIp()+":"+meDataSource.getPort()+"/"+meDataSource.getDb()+"?useUnicode=true&characterEncoding=UTF-8");
		dataSource.setUsername(meDataSource.getUn());
		dataSource.setPassword(meDataSource.getPw());
		return dataSource;
	}
	
	private Properties getHibernateProperties() {
	    Properties properties = new Properties();
	    properties.put("hibernate.show_sql", "true");
	    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5Dialect");
	    properties.put("hibernate.format_sql", "true");
	    properties.put("hibernate.jdbc.batch_size", "50");
	    properties.put("hibernate.cache.use_second_level_cache", "true");
	    properties.put("hibernate.c3p0.timeout", "3000");
	    return properties;
	}
	
	public void shutdown(MeDataSource meDataSource){
		getSessionFactory(meDataSource).close();
	}
	
}
