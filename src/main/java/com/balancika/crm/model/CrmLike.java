package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name="crm_user_like_collaboration")
public class CrmLike implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LK_ID")
	private int likeId;
	
	@Column(name="LK_CBID")
	private int collapId;
	
	@Column(name="LK_UserName")
	private String username;
	
	@Transient
	private MeDataSource dataSource;

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getCollapId() {
		return collapId;
	}

	public void setCollapId(int collapId) {
		this.collapId = collapId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public final MeDataSource getDataSource() {
		return dataSource;
	}

	public final void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}

}
