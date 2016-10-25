package com.balancika.crm.configuration;

import javax.sql.DataSource;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.aop.target.HotSwappableTargetSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

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
		//if(config != null){
			realDataSource = getDataSourceFromBasicDataSource(config);
		//}
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
		  HotSwappableTargetSource swapper = (HotSwappableTargetSource) ctx.getBean("swappableDataSource");
		  swapper.swap(realDataSource);
		  return (DataSource) ctx.getBean("dataSource");
	}
}
