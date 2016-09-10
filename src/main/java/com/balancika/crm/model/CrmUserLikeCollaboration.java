package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="crm_user_like_collaboration")
public class CrmUserLikeCollaboration implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="LK_ID")
	private int likeId;
	
	@Column(name="LK_CBID", nullable = false)
	private int likeColId;
	
	@NotEmpty
	@Column(name = "LK_UID", nullable = false)
	private String likeUserId;

	public int getLikeId() {
		return likeId;
	}

	public void setLikeId(int likeId) {
		this.likeId = likeId;
	}

	public int getLikeColId() {
		return likeColId;
	}

	public void setLikeColId(int likeColId) {
		this.likeColId = likeColId;
	}

	public String getLikeUserId() {
		return likeUserId;
	}

	public void setLikeUserId(String likeUserId) {
		this.likeUserId = likeUserId;
	}
}
