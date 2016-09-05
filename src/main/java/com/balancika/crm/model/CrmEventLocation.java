package com.balancika.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name = "crm_location")
public class CrmEventLocation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="LO_ID", unique = true, nullable = false)
	private String loId;
	
	@NotEmpty
	@Column(name="LO_Name", unique = true, nullable =false)
	private String loName;
	
	@Column(name="LO_No")
	private String loNo;
	
	@Column(name="LO_Street")
	private String loStreet;
	
	@Column(name="LO_Village")
	private String village;
	
	@Column(name="LO_Commune")
	private String loCommune;
	
	@Column(name="LO_District")
	private String loDistrict;
	
	@Column(name="LO_City")
	private String loCity;
	
	@Column(name="LO_State")
	private String loState;
	
	@Column(name="LO_Country")
	private String loCountry;
	
	@Column(name="LO_CBy", updatable = false)
	private String loCreateBy;
	
	@Type(type="date")
	@Column(name="LO_CDate", updatable = false)
	private Date loCreateDate;
	
	@Column(name="LO_MBy")
	private String loModifiedBy;
	
	@Type(type="date")
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="LO_MDate", insertable = false, updatable = false)
	private Date loModifiedDate;

	public String getLoId() {
		return loId;
	}

	public void setLoId(String loId) {
		this.loId = loId;
	}

	public String getLoName() {
		return loName;
	}

	public void setLoName(String loName) {
		this.loName = loName;
	}

	public String getLoNo() {
		return loNo;
	}

	public void setLoNo(String loNo) {
		this.loNo = loNo;
	}

	public String getLoStreet() {
		return loStreet;
	}

	public void setLoStreet(String loStreet) {
		this.loStreet = loStreet;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getLoCommune() {
		return loCommune;
	}

	public void setLoCommune(String loCommune) {
		this.loCommune = loCommune;
	}

	public String getLoDistrict() {
		return loDistrict;
	}

	public void setLoDistrict(String loDistrict) {
		this.loDistrict = loDistrict;
	}

	public String getLoCity() {
		return loCity;
	}

	public void setLoCity(String loCity) {
		this.loCity = loCity;
	}

	public String getLoState() {
		return loState;
	}

	public void setLoState(String loState) {
		this.loState = loState;
	}

	public String getLoCountry() {
		return loCountry;
	}

	public void setLoCountry(String loCountry) {
		this.loCountry = loCountry;
	}

	public String getLoCreateBy() {
		return loCreateBy;
	}

	public void setLoCreateBy(String loCreateBy) {
		this.loCreateBy = loCreateBy;
	}

	public Date getLoCreateDate() {
		return loCreateDate;
	}

	public void setLoCreateDate(Date loCreateDate) {
		this.loCreateDate = loCreateDate;
	}

	public String getLoModifiedBy() {
		return loModifiedBy;
	}

	public void setLoModifiedBy(String loModifiedBy) {
		this.loModifiedBy = loModifiedBy;
	}

	public Date getLoModifiedDate() {
		return loModifiedDate;
	}

	public void setLoModifiedDate(Date loModifiedDate) {
		this.loModifiedDate = loModifiedDate;
	}
}
