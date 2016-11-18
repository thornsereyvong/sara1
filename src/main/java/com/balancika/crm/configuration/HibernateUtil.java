package com.balancika.crm.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;

import com.balancika.crm.model.MeDataSource;

public class HibernateUtil {
	public static LocalSessionFactoryBean getSessionFactory(MeDataSource meDataSource){
		LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
        sessionFactory.setDataSource(dataSource(meDataSource));
        sessionFactory.setPackagesToScan(new String[] {"com.balancika.crm.model"});
        sessionFactory.setHibernateProperties(getHibernateProperties());
        return sessionFactory;
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
	    //properties.put("hibernate.connection.autocommit", "true");
	    properties.put("hibernate.c3p0.max_size", 20);
	    properties.put("hibernate.c3p0.min_size", 5);
	    properties.put("hibernate.c3p0.timeout", 1800);
	    properties.put("hibernate.c3p0.max_statements", 50);
	    properties.put("hibernate.c3p0.idle_test_period", 3000);
	    return properties;
	}
	
	public static void shutdown(MeDataSource meDataSource){
		//getSessionFactory(meDataSource).close();
	}
}
