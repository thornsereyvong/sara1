package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name="crm_activity")
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
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="AC_Date")
	private LocalDateTime createDate;
	
	@Transient
	private String cDate;
	
	@Transient
	private MeDataSource meDataSource;

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
