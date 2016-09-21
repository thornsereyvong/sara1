package com.balancika.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="crm_contact")
public class CrmContact implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="CO_ID", nullable = false, length = 11)
	private String conID;
	
	@Column(name="CO_Salutation")
	private String conSalutation;
	
	@NotEmpty
	@Column(name="CO_FirstName", nullable = false)
	private String conFirstname;
	
	@NotEmpty
	@Column(name="CO_LastName", nullable = false)
	private String conLastname;
	
	@NotEmpty
	@Column(name="CO_Phone", nullable = false)
	private String conPhone;
	
	@Column(name="CO_Mobile")
	private String conMobile;
	
	@Column(name="CO_Email")
	private String conEmial;

	@Column(name="CO_Title")
	private String conTitle;
	
	@Column(name="CO_Department")
	private String conDepartment;
	
	@Column(name="CO_No")
	private String conNo;
	
	@Column(name="CO_Street")
	private String conStreet;
	
	@Column(name="CO_Village")
	private String conVillage;
	
	@Column(name="CO_Commune")
	private String conCommune;
	
	@Column(name="CO_District")
	private String conDistrict;
	
	@Column(name="CO_City")
	private String conCity;
	
	@Column(name="CO_State")
	private String conState;
	
	@Column(name="CO_Country")
	private String conCountry;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CO_ATo", nullable = true)
	private CrmUser conAssignTo;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CO_LeadSource", nullable = true)
	private CrmLeadSource conLeadSource;
	
	@OneToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CO_ReportTo", nullable = true)
	private CrmContact conReportTo;
	
	@Column(name="CO_CBy", updatable = false)
	private String conCreateBy;
	
	@Type(type="date")
	@Column(name="CO_CDate", updatable = false)
	private Date conCreateDate;
	
	@Column(name="CO_MBy")
	private String conModifyBy;
	
	@Type(type="date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CO_MDate", insertable = false, updatable = false)
	private Date modifyDate;
	
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CO_CustID", nullable = true)
	private CrmCustomer customer;

	public String getConID() {
		return conID;
	}

	public void setConID(String conID) {
		this.conID = conID;
	}


	public String getConFirstname() {
		return conFirstname;
	}


	public void setConFirstname(String conFirstname) {
		this.conFirstname = conFirstname;
	}


	public String getConLastname() {
		return conLastname;
	}


	public void setConLastname(String conLastname) {
		this.conLastname = conLastname;
	}


	public String getConPhone() {
		return conPhone;
	}


	public void setConPhone(String conPhone) {
		this.conPhone = conPhone;
	}


	public String getConMobile() {
		return conMobile;
	}


	public void setConMobile(String conMobile) {
		this.conMobile = conMobile;
	}


	public String getConEmial() {
		return conEmial;
	}


	public void setConEmial(String conEmial) {
		this.conEmial = conEmial;
	}


	public String getConTitle() {
		return conTitle;
	}


	public void setConTitle(String conTitle) {
		this.conTitle = conTitle;
	}


	public String getConDepartment() {
		return conDepartment;
	}


	public void setConDepartment(String conDepartment) {
		this.conDepartment = conDepartment;
	}


	public String getConNo() {
		return conNo;
	}


	public void setConNo(String conNo) {
		this.conNo = conNo;
	}


	public String getConStreet() {
		return conStreet;
	}


	public void setConStreet(String conStreet) {
		this.conStreet = conStreet;
	}


	public String getConVillage() {
		return conVillage;
	}


	public void setConVillage(String conVillage) {
		this.conVillage = conVillage;
	}


	public String getConCommune() {
		return conCommune;
	}


	public void setConCommune(String conCommune) {
		this.conCommune = conCommune;
	}


	public String getConDistrict() {
		return conDistrict;
	}


	public void setConDistrict(String conDistrict) {
		this.conDistrict = conDistrict;
	}


	public String getConCity() {
		return conCity;
	}


	public void setConCity(String conCity) {
		this.conCity = conCity;
	}


	public String getConState() {
		return conState;
	}


	public void setConState(String conState) {
		this.conState = conState;
	}


	public String getConCountry() {
		return conCountry;
	}


	public void setConCountry(String conCountry) {
		this.conCountry = conCountry;
	}


	public CrmUser getConAssignTo() {
		return conAssignTo;
	}


	public void setConAssignTo(CrmUser conAssignTo) {
		this.conAssignTo = conAssignTo;
	}


	public CrmLeadSource getConLeadSource() {
		return conLeadSource;
	}


	public void setConLeadSource(CrmLeadSource conLeadSource) {
		this.conLeadSource = conLeadSource;
	}


	public CrmContact getConReportTo() {
		return conReportTo;
	}


	public void setConReportTo(CrmContact conReportTo) {
		this.conReportTo = conReportTo;
	}


	public String getConCreateBy() {
		return conCreateBy;
	}


	public void setConCreateBy(String conCreateBy) {
		this.conCreateBy = conCreateBy;
	}


	public Date getConCreateDate() {
		return conCreateDate;
	}


	public void setConCreateDate(Date conCreateDate) {
		this.conCreateDate = conCreateDate;
	}


	public String getConModifyBy() {
		return conModifyBy;
	}


	public void setConModifyBy(String conModifyBy) {
		this.conModifyBy = conModifyBy;
	}


	public Date getModifyDate() {
		return modifyDate;
	}


	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}


	public CrmCustomer getCustomer() {
		return customer;
	}


	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}

	/**
	 * @return the conSalutation
	 */
	public String getConSalutation() {
		return conSalutation;
	}

	/**
	 * @param conSalutation the conSalutation to set
	 */
	public void setConSalutation(String conSalutation) {
		this.conSalutation = conSalutation;
	}
}
