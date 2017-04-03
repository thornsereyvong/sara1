package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity(name="CrmMeetingAudio")
@Table(name="crm_meeting_audio")
public class CrmMeetingAudio implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="AU_ID")
	private int audioId;
	
	@Column(name="AU_Path")
	private String audioPath;

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
}
