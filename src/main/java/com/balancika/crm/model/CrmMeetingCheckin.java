package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTable;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity(name="CrmMeetingCheckin")
@Table(name="crm_meeting")
@SecondaryTables({
		@SecondaryTable(name="crm_meeting_audio", pkJoinColumns = @PrimaryKeyJoinColumn(name="M_ID")),
		@SecondaryTable(name="crm_meeting_image", pkJoinColumns = @PrimaryKeyJoinColumn(name="M_ID"))
	})
public class CrmMeetingCheckin implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="MS_ID")
	private String meetId; 
	
	@Column(name="M_CheckIn_Coordonation")
	private String meetCoordonation;
	
	@Column(table="crm_meeting_audio",name="AU_ID")
	private int audioId;
	
	@Column(table="crm_meeting_audio", name="AU_Path")
	private String audioPath;
	
	@Column(table="crm_meeting_image",name="IMG_ID")
	private int imgId;
	
	@Column(table="crm_meeting_image", name="IMG_Path")
	private String imgPath;
	
	@Transient
	private MeDataSource dataSource;

	public String getMeetId() {
		return meetId;
	}

	public void setMeetId(String meetId) {
		this.meetId = meetId;
	}

	public String getMeetCoordonation() {
		return meetCoordonation;
	}

	public void setMeetCoordonation(String meetCoordonation) {
		this.meetCoordonation = meetCoordonation;
	}

	public int getAudioId() {
		return audioId;
	}

	public void setAudioId(int audioId) {
		this.audioId = audioId;
	}

	public String getAudioPath() {
		return audioPath;
	}

	public void setAudioPath(String audioPath) {
		this.audioPath = audioPath;
	}

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

	public MeDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
