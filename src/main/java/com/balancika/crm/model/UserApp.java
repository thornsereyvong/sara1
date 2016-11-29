package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tbluserapp")
public class UserApp implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "UID")
	private String userId;
	
	@Column(name="AppID")
	private String appId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}
}
