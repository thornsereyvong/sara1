package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "tblshipaddress")
public class CrmShipAddress implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="moduleid", nullable = false)
	private String moduleId;
	
	@Id
	@Column(name="docid")
	private String docId;
	
	@Id
	@Column(name="shipId")
	private String shipId;
	
	@Column(name = "shipname")
	private String shipName;
	
	@Column(name = "inactive")
	private Short inactive;
	
	@Transient
	private MeDataSource dataSource;

	public String getModuleId() {
		return moduleId;
	}

	public void setModuleId(String moduleId) {
		this.moduleId = moduleId;
	}

	public String getShipId() {
		return shipId;
	}

	public void setShipId(String shipId) {
		this.shipId = shipId;
	}

	public String getShipName() {
		return shipName;
	}

	public void setShipName(String shipName) {
		this.shipName = shipName;
	}

	public Short getInactive() {
		return inactive;
	}

	public void setInactive(Short inactive) {
		this.inactive = inactive;
	}

	public String getDocId() {
		return docId;
	}

	public void setDocId(String docId) {
		this.docId = docId;
	}

	public final MeDataSource getDataSource() {
		return dataSource;
	}

	public final void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
