package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name="crm_event")
public class CrmEvent implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="EV_ID", unique = true, nullable = false)
	private String evId;
	
	@NotEmpty
	@Column(name="EV_Name", unique = true)
	private String evName;
	

	@Column(name="EV_LocationID", nullable = true)
	private String evlocation;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="EV_StartDate")
	private LocalDateTime evStartDate;
	
	@Column(name="EV_EndDate")
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	private LocalDateTime evEndDate;
	
	@Column(name="EV_Duration")
	private String evDuration;
	
	@Column(name="EV_Budget")
	private double evBudget;
	
	@Column(name="EV_Description")
	private String evDes;
	
	@Column(name="EV_AssignTo")
	private String assignTo;
	
	@Column(name="EV_CBy", updatable = false)
	private String evCreateBy;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="EV_CDate", updatable = false)
	private LocalDateTime evCreateDate;
	
	@Column(name="EV_MBy")
	private String evModifiedBy;
	
	@Type(type="date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EV_MDate", insertable = false, updatable = false)
	private Date evModifiedDate;
	
	@Column(name="EV_RToType")
	private String evRelatedToType;
	
	@Column(name="EV_RToID")
	private String evRelatedToID;

	public String getEvId() {
		return evId;
	}

	public void setEvId(String evId) {
		this.evId = evId;
	}

	public String getEvName() {
		return evName;
	}

	public void setEvName(String evName) {
		this.evName = evName;
	}

	public String getEvlocation() {
		return evlocation;
	}

	public void setEvlocation(String evlocation) {
		this.evlocation = evlocation;
	}

	public LocalDateTime getEvStartDate() {
		return evStartDate;
	}

	public void setEvStartDate(LocalDateTime evStartDate) {
		this.evStartDate = evStartDate;
	}

	public LocalDateTime getEvEndDate() {
		return evEndDate;
	}

	public void setEvEndDate(LocalDateTime evEndDate) {
		this.evEndDate = evEndDate;
	}

	public String getEvDuration() {
		return evDuration;
	}

	public void setEvDuration(String evDuration) {
		this.evDuration = evDuration;
	}

	public double getEvBudget() {
		return evBudget;
	}

	public void setEvBudget(double evBudget) {
		this.evBudget = evBudget;
	}

	public String getEvDes() {
		return evDes;
	}

	public void setEvDes(String evDes) {
		this.evDes = evDes;
	}

	public String getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(String assignTo) {
		this.assignTo = assignTo;
	}

	public String getEvCreateBy() {
		return evCreateBy;
	}

	public void setEvCreateBy(String evCreateBy) {
		this.evCreateBy = evCreateBy;
	}

	public LocalDateTime getEvCreateDate() {
		return evCreateDate;
	}

	public void setEvCreateDate(LocalDateTime evCreateDate) {
		this.evCreateDate = evCreateDate;
	}

	public String getEvModifiedBy() {
		return evModifiedBy;
	}

	public void setEvModifiedBy(String evModifiedBy) {
		this.evModifiedBy = evModifiedBy;
	}

	public Date getEvModifiedDate() {
		return evModifiedDate;
	}

	public void setEvModifiedDate(Date evModifiedDate) {
		this.evModifiedDate = evModifiedDate;
	}

	public String getEvRelatedToType() {
		return evRelatedToType;
	}

	public void setEvRelatedToType(String evRelatedToType) {
		this.evRelatedToType = evRelatedToType;
	}

	public String getEvRelatedToID() {
		return evRelatedToID;
	}

	public void setEvRelatedToID(String evRelatedToID) {
		this.evRelatedToID = evRelatedToID;
	}
}
