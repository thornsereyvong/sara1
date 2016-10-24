package com.balancika.crm.configuration;

import javax.sql.DataSource;

import org.apache.tomcat.dbcp.dbcp.BasicDataSource;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;

import com.balancika.crm.model.CrmDatabaseConfiguration;

public class DataSourceFactory {
	
	@Autowired
	private CrmDatabaseConfiguration config;
	
	private ApplicationContext ctx = null;
	
	public DataSourceFactory(ApplicationContext _ctx) {
		  super();
		  this.ctx = _ctx;
		 }

	public DataSource dataSource(){
		DataSource realDataSource = null;
		if(config != null){
			realDataSource = getDataSourceFromBasicDataSource(config);
		}else{
			config.setDbIP("192.168.0.2");
			config.setDbName("bmg_crm");
			config.setDbPort("3306");
			config.setDbPassword("Pa$$w0rd");
			config.setDbUsername("posadmin");
			realDataSource = getDataSourceFromBasicDataSource(config);
		}
		return swapToDataSource(realDataSource);
	}
	
	public DataSource getDataSourceFromBasicDataSource(final CrmDatabaseConfiguration config){
		  BasicDataSource realDataSource = new BasicDataSource();
		  realDataSource.setDriverClassName("com.mysql.jdbc.Driver");
		  realDataSource.setUrl("jdbc:mysql://"+config.getDbIP()+":"+config.getDbPort()+"/"+config.getDbName()+"?useUnicode=true&characterEncoding=UTF-8");
		  realDataSource.setUsername(config.getDbUsername());
		  realDataSource.setPassword(config.getDbPassword());
		  return swapToDataSource(realDataSource);
	}
	
	private DataSource swapToDataSource(final DataSource realDataSource) {
		  Assert.notNull(realDataSource, "Error defining the real dataSource.");
		  HotSwappableTargetSource swapper = (HotSwappableTargetSource) ctx.getBean("swappableDataSource");
		  swapper.swap(realDataSource);
		  return (DataSource) ctx.getBean("dataSource");
		 }
}
