package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="crm_numberprefix")
public class CrmModule implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="ModuleID")
	private String moduleId;
	
	@Column(name="Remarks")
	private String moduleName;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getModuleName() {
		return moduleName;
	}

	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
}
