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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name="crm_lead")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
public class CrmLead implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="LA_ID", unique = true, nullable = false)
	@NotEmpty
	private String leadID;
	
	@Column(name="LA_Salutation")
	private String salutation;
	
	@NotEmpty
	@Column(name="LA_FirstName")
	private String firstName;
	
	@NotEmpty
	@Column(name="LA_LastName")
	private String lastName;

	@Column(name="LA_Title")
	private String title;
	
	@Column(name="LA_Department")
	private String department;
	
	@Column(name="LA_Phone")
	private String phone;
	
	@Column(name="LA_Mobile")
	private String mobile;
	
	@Column(name="LA_Website")
	private String website;
	
	@Column(name="LA_AccountName")
	private String accountName;
	
	@Column(name="LA_Email")
	private String Email;
	
	@Column(name="LA_No")
	private String no;
	
	@Column(name="LA_Street")
	private String street;
	
	@Column(name="LA_Village")
	private String village;
	
	@Column(name="LA_Commune")
	private String commune;
	
	@Column(name="LA_District")
	private String district;
	
	@Column(name="LA_City")
	private String city;
	
	@Column(name="LA_State")
	private String state;
	
	@Column(name="LA_Country")
	private String country;
	
	@Column(name="LA_Des")
	private String description;
	
	//relationship many to one between table crm_lead and crm_lead_status 
	@ManyToOne(targetEntity = CrmLeadStatus.class, fetch = FetchType.EAGER) 
	@JoinColumn(name="LA_StatusID",
			nullable = true)
	private CrmLeadStatus status;
	
	/* relationship many to one between table crm_lead and crm_industry */
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name="LA_IndustID", nullable = true)
	private CrmIndustry industry;
	
	/* relationship many to one between table crm_lead and crm_lead_soruce */
	@ManyToOne(fetch = FetchType.EAGER) 
	@JoinColumn(name="LA_SourceID", nullable = true)
	private CrmLeadSource source;
	
	/* relationship many to one between table crm_lead and crm_camp */
	@ManyToOne(optional = true , targetEntity = CrmCampaign.class, fetch = FetchType.EAGER) 
	@JoinColumn(name="LA_CA_ID",
			nullable = true)
	private CrmCampaign campaign;

	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="LA_ATo")
	private CrmUser assignTo;
	
	@Column(name="LA_CBy", updatable = false)
	private String createBy;
	
	@Column(name="LA_CDate", updatable = false)
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	private LocalDateTime createDate;
	
	@Transient
	private String convertCreateDate;
	
	@Column(name="LA_MDate", insertable = false, updatable = false)
	@Temporal(TemporalType.TIMESTAMP)
	@Type(type="date")
	private Date modifyDate;
	
	@Column(name="LA_MBy")
	private String modifyBy;
	
	@Transient
	private MeDataSource meDataSource;

	public String getLeadID() {
		return leadID;
	}

	public void setLeadID(String leadID) {
		this.leadID = leadID;
	}

	public String getSalutation() {
		return salutation;
	}

	public void setSalutation(String salutation) {
		this.salutation = salutation;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getVillage() {
		return village;
	}

	public void setVillage(String village) {
		this.village = village;
	}

	public String getCommune() {
		return commune;
	}

	public void setCommune(String commune) {
		this.commune = commune;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}


	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CrmLeadStatus getStatus() {
		return status;
	}

	public void setStatus(CrmLeadStatus status) {
		this.status = status;
	}
	

	public CrmIndustry getIndustry() {
		return industry;
	}

	public void setIndustry(CrmIndustry industry) {
		this.industry = industry;
	}


	public CrmUser getAssignTo() {
		return assignTo;
	}

	public void setAssignTo(CrmUser assignTo) {
		this.assignTo = assignTo;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public String getConvertCreateDate() {
		return convertCreateDate;
	}

	public void setConvertCreateDate(String convertCreateDate) {
		this.convertCreateDate = convertCreateDate;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}
	
	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public CrmCampaign getCampaign() {
		return campaign;
	}

	public void setCampaign(CrmCampaign campaign) {
		this.campaign = campaign;
	}

	public CrmLeadSource getSource() {
		return source;
	}

	public void setSource(CrmLeadSource source) {
		this.source = source;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
