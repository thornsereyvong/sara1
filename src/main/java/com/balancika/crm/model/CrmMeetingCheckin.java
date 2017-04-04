package com.balancika.crm.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="CrmMeetingCheckin")
@Table(name="crm_meeting")
public class CrmMeetingCheckin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="M_ID")
	private String meetId; 
	
	@Column(name="M_CheckIn_Longitude")
	private String meetLongitude;
	
	@Column(name="M_CheckIn_Latitude")
	private String meetLatitude;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="M_ID", nullable = false)
	private List<CrmMeetingAudio> audio;
	
	@OneToMany(fetch=FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name="M_ID", nullable = false)
	private List<CrmMeetingImage> images;
	
	@Column(name="M_StatusID")
	private int statusId;
	
	@Column(name="M_Des")
	private String des;
	
	@Transient
	private MeDataSource dataSource;

	public String getMeetId() {
		return meetId;
	}

	public void setMeetId(String meetId) {
		this.meetId = meetId;
	}

	public String getMeetLongitude() {
		return meetLongitude;
	}

	public void setMeetLongitude(String meetLongitude) {
		this.meetLongitude = meetLongitude;
	}

	public String getMeetLatitude() {
		return meetLatitude;
	}

	public void setMeetLatitude(String meetLatitude) {
		this.meetLatitude = meetLatitude;
	}

	public List<CrmMeetingAudio> getAudio() {
		return audio;
	}

	public void setAudio(List<CrmMeetingAudio> audio) {
		this.audio = audio;
	}

	public List<CrmMeetingImage> getImages() {
		return images;
	}

	public void setImages(List<CrmMeetingImage> images) {
		this.images = images;
	}

	public int getStatusId() {
		return statusId;
	}

	public void setStatusId(int statusId) {
		this.statusId = statusId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public MeDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}

}