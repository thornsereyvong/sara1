package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Type;
import org.springframework.stereotype.Repository;

@Entity
@Table(name="crm_activity")
@Repository
public class CrmUserActivity implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="AC_ID")
	
	
	private int actId;

	@Column(name="AC_Action")
	private String action;
	
	@Column(name="AC_ModuleName")
	private String moduleName;
	
	@Column(name="AC_ModuleID")
	private String moduleId;
	
	@Column(name="AC_ByUser")
	private String byUser;
	
	
	@Column(name = "AC_Date", updatable = false, insertable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type = "date")
	private LocalDateTime createDate;
	
	@Transient
	private String cDate;
	
	@Transient
	private MeDataSource meDataSource;
	
	
	public CrmUserActivity getActivity(MeDataSource dataSource, String action, String moduleName, String moduleId){		
		CrmUserActivity act = new CrmUserActivity();
		act.setMeDataSource(dataSource);
		act.setAction(action);
		act.setModuleName(moduleName);
		act.setModuleId(moduleId);
		act.setByUser(dataSource.getUserid());		
		return act;
		
	}

	public int getActId() {
		return actId;
	}

	public void setActId(int actId) {
		this.actId = actId;
	}

	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getByUser() {
		return byUser;
	}

	public void setByUser(String byUser) {
		this.byUser = byUser;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getcDate() {
		return cDate;
	}

	public void setcDate(String cDate) {
		this.cDate = cDate;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
