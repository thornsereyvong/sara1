package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Transient;

public class CrmMessage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="Code")
	private String code;
	
	@Column(name="Msg")
	private String msg;
	
	@Transient
	private MeDataSource meDataSource;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
