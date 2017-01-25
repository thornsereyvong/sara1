package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="CrmUserLogin")
@Table(name = "tbluser")
@SecondaryTable(name="tbluserapp", pkJoinColumns = @PrimaryKeyJoinColumn(name="UID"))
public class CrmUserLogin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty
	@Column(name="UID", nullable = false, unique = true, length = 11)
	private String userID;
	
	@NotEmpty
	@Column(name="UName", nullable = false, unique = true)
	private String username;
	
	@NotEmpty
	@Column(name="UPassword", nullable = false)
	private String password;
	
	@Column(name="UStatus", nullable = false, length = 1)
	private int status;

	@Column(name="UParentID")
	private String parentID;
	
	@Column(table="tbluserapp", name="AppID")
	private String appId;
		
	@Transient
	private MeDataSource dataSource;
	
	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public String getParentID() {
		return parentID;
	}
	public void setParentID(String parentID) {
		this.parentID = parentID;
	}

	public MeDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
