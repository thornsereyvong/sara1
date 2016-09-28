package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbllocation")
public class AmeLocation implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="SysCode")
	private String sysCode;
	
	@Id
	@Column(name="LocationID")
	private String locationId;
	
	@Column(name="Des")
	private String locationName;

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getLocationId() {
		return locationId;
	}

	public void setLocationId(String locationId) {
		this.locationId = locationId;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}	

}
