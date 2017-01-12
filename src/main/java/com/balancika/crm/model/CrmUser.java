package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity(name="CrmUser")
@Table(name = "tbluser")
public class CrmUser implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty
	@Column(name="UID", nullable = false, unique = true, length = 11)
	private String userID;
	
	@NotEmpty
	@Column(name="UName", nullable = false, unique = true)
	private String username;
	
	@Column(name="UPassword", updatable=false)
	private String password;
	
	@Column(name="UType")
	private String userType;
	
	@Column(name="UKey")
	private String userKey;
	
	@Column(name="UStatus", nullable = false, length = 1)
	private int status;

	@Column(name="UParentID", updatable=false)
	private String parentID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="URoleID", nullable = true)
	private CrmRole role;
	
	@ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="UID", nullable = false, insertable = false, updatable = false)
	private UserApp userApp;
		
	@Transient
	private MeDataSource dataSource;

	public CrmUser(){
		
	}
	
	
	public UserApp getUserApp() {
		return userApp;
	}


	public void setUserApp(UserApp userApp) {
		this.userApp = userApp;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserKey() {
		return userKey;
	}

	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
	
	public CrmRole getRole() {
		return role;
	}

	public void setRole(CrmRole role) {
		this.role = role;
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
