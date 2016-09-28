package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbluom")
public class AmeUom implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Column(name="SysCode")
	private String sysCode;
	
	@Id
	@Column(name="UomID")
	private String uomId;
	
	@Column(name="Des")
	private String Des;
	
	@Column(name="Remark")
	private String remark;

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getUomId() {
		return uomId;
	}

	public void setUomId(String uomId) {
		this.uomId = uomId;
	}

	public String getDes() {
		return Des;
	}

	public void setDes(String des) {
		Des = des;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
