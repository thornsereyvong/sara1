package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="EV_LocationID", nullable = true)
	private CrmEventLocation evlocation;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="EV_StartDate")
	private LocalDateTime evStartDate;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="EV_EndDate")
	private LocalDateTime evEndDate;
	
	@Column(name="EV_RToType")
	private String evRelatedToModuleType;
	
	@Column(name="EV_RToID")
	private String evRelatedToModuleId;
	
	@Transient
	private String startDate;
	
	@Transient
	private String endDate;
	
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
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="EV_CDate", updatable = false)
	private LocalDateTime evCreateDate;
	
	@Column(name="EV_MBy")
	private String evModifiedBy;
	
	@Type(type="date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="EV_MDate", insertable = false, updatable = false)
	private Date evModifiedDate;
	
	@Transient
	private MeDataSource meDataSource;

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

	public String getStartDate() {
		return startDate;
	}
	
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}
	
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEvRelatedToModuleType() {
		return evRelatedToModuleType;
	}

	public void setEvRelatedToModuleType(String evRelatedToModuleType) {
		this.evRelatedToModuleType = evRelatedToModuleType;
	}

	public String getEvRelatedToModuleId() {
		return evRelatedToModuleId;
	}

	public void setEvRelatedToModuleId(String evRelatedToModuleId) {
		this.evRelatedToModuleId = evRelatedToModuleId;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
