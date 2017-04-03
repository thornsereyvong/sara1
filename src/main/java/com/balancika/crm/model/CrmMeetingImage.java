package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CrmMeetingImage")
@Table(name="crm_meeting_image")
public class CrmMeetingImage implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="IMG_ID")
	private int imgId;
	
	@Column(name="IMG_Path")
	private String imgPath;

	public int getImgId() {
		return imgId;
	}

	public void setImgId(int imgId) {
		this.imgId = imgId;
	}

	public String getImgPath() {
		return imgPath;
	}

	public void setImgPath(String imgPath) {
		this.imgPath = imgPath;
	}
}
