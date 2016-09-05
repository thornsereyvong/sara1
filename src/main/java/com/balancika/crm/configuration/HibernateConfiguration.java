package com.balancika.crm.configuration;

import java.util.Properties;

import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.balancika.crm.model.CrmDatabaseConfiguration;

@Configuration
@EnableTransactionManagement
@ComponentScan({ "com.balancika.crm.configuration" })
@PropertySource(value = { "classpath:application.properties"})
public class HibernateConfiguration {

	    @Autowired
	    private Environment environment;
	  
	    @Bean
	    public LocalSessionFactoryBean sessionFactory() {
	        LocalSessionFactoryBean sessionFactory = new LocalSessionFactoryBean();
	        sessionFactory.setDataSource(dataSource());
	        sessionFactory.setPackagesToScan(new String[] {"com.balancika.crm.model"});
	        sessionFactory.setHibernateProperties(hibernateProperties());
	        return sessionFactory;
	     }
		
	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(environment.getRequiredProperty("jdbc.driverClassName"));
	        dataSource.setUrl(environment.getRequiredProperty("jdbc.url"));
	        dataSource.setUsername(environment.getRequiredProperty("jdbc.username"));
	        dataSource.setPassword(environment.getRequiredProperty("jdbc.password"));
	        
	        /*dataSource.setDriverClassName("com.mysql.jdbc.Driver");
	        dataSource.setUrl("jdbc:mysql://"+databaseConfiguration().getDbIP()+":"+databaseConfiguration().getDbPort()+"/"+databaseConfiguration().getDbName()+"?createDatabaseIfNotExist=true");
	        dataSource.setUsername(databaseConfiguration().getDbUsername());
	        dataSource.setPassword(databaseConfiguration().getDbPassword());*/
	        
	        return dataSource;
	    }
	    
	    private Properties hibernateProperties() {
	        Properties properties = new Properties();
	        properties.put("hibernate.dialect", environment.getRequiredProperty("hibernate.dialect"));
	        properties.put("hibernate.show_sql", environment.getRequiredProperty("hibernate.show_sql"));
	        properties.put("hibernate.format_sql", environment.getRequiredProperty("hibernate.format_sql"));
	        properties.put("hibernate.jdbc.batch_size", environment.getRequiredProperty("hibernate.jdbc.batch_size"));
	        properties.put("hibernate.cache.use_second_level_cache", environment.getRequiredProperty("hibernate.cache.use_second_level_cache"));
	        properties.put("hibernate.c3p0.max_size", environment.getRequiredProperty("hibernate.c3p0.max_size"));
	        properties.put("hibernate.c3p0.min_size", environment.getRequiredProperty("hibernate.c3p0.min_size"));
	        properties.put("hibernate.c3p0.timeout", environment.getRequiredProperty("hibernate.c3p0.timeout"));
	        properties.put("hibernate.c3p0.max_statements", environment.getRequiredProperty("hibernate.c3p0.max_statements"));
	        properties.put("hibernate.c3p0.idle_test_period", environment.getRequiredProperty("hibernate.c3p0.idle_test_period"));
	        properties.put("hibernate.c3p0.acquire_increment", environment.getRequiredProperty("hibernate.c3p0.acquire_increment"));
	        return properties;        
	    }
	    
		@Bean
	    @Autowired
	    public HibernateTransactionManager transactionManager(SessionFactory s) {
	       HibernateTransactionManager txManager = new HibernateTransactionManager();
	       txManager.setSessionFactory(s);
	       return txManager;
	    }
		
		@Bean
		public CrmDatabaseConfiguration databaseConfiguration(){ 
			return new CrmDatabaseConfiguration();
		}
		
}