package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "crm_user")
public class CrmUser implements Serializable{

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
	
	@Column(name="UType")
	private String userType;
	
	@Column(name="UKey")
	private String userKey;
	
	@Column(name="UStatus", nullable = false, length = 1)
	private int status;

	@Column(name="UParentID")
	private String parentID;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="URoleID", nullable = false)
	private CrmRole role;
	
	/*@ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinTable(name = "crm_user_db",
				joinColumns = {@JoinColumn( name = "UID")},
				inverseJoinColumns={@JoinColumn(name = "DB_ID")})
	@Fetch(value = FetchMode.SUBSELECT)
	private List<CrmDatabaseConfiguration> database;*/

	public CrmUser(){
		
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
	/*public List<CrmDatabaseConfiguration> getDatabase() {
		return database;
	}
	public void setDatabase(List<CrmDatabaseConfiguration> database) {
		this.database = database;
	}*/
}