package com.balancika.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="EV_LocationID", nullable = true)
	private CrmEventLocation evlocation;
	
	@Type(type="date")
	@Temporal(TemporalType.DATE)
	@Column(name="EV_StartDate")
	private Date evStartDate;
	
	@Type(type="date")
	@Column(name="EV_EndDate")
	@Temporal(TemporalType.DATE)
	private Date evEndDate;
	
	@Column(name="EV_Duration")
	private String evDuration;
	
	@Column(name="EV_Budget")
	private double evBudget;
	
	@Column(name="EV_Description")
	private String evDes;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="EV_AssignTo")
	private CrmUser assignTo;
	
	@Column(name="EV_CBy", updatable = false)
	private String evCreateBy;
	
	@Type(type="date")
	@Temporal(TemporalType.DATE)
	@Column(name="EV_CDate", updatable = false)
	private Date evCreateDate;
	
	@Column(name="EV_MBy")
	private String evModifiedBy;
	
	@Type(type="date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EV_MDate", insertable = false, updatable = false)
	private Date evModifiedDate;

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

	public CrmEventLocation getEvlocation() {
		return evlocation;
	}

	public void setEvlocation(CrmEventLocation evlocation) {
		this.evlocation = evlocation;
	}

	public Date getEvStartDate() {
		return evStartDate;
	}

	public void setEvStartDate(Date evStartDate) {
		this.evStartDate = evStartDate;
	}

	public Date getEvEndDate() {
		return evEndDate;
	}

	public void setEvEndDate(Date evEndDate) {
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

	public CrmUser getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(CrmUser assignTo) {
		this.assignTo = assignTo;
	}

	public String getEvCreateBy() {
		return evCreateBy;
	}

	public void setEvCreateBy(String evCreateBy) {
		this.evCreateBy = evCreateBy;
	}

	public Date getEvCreateDate() {
		return evCreateDate;
	}

	public void setEvCreateDate(Date evCreateDate) {
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
}