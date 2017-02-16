package com.balancika.crm.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity(name="CrmLeadProject")
@Table(name="crm_lead_project")
public class CrmLeadProject implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="LP_ID")
	private int id;
	
	@Column(name="LP_Name")
	private String name;
	
	@Column(name="LP_AccountManager")
	private String accountManager;
	
	@Column(name="LP_CompanyName")
	private String companyName;
	
	@Column(name="LP_TypeBiz")
	private String typeBiz;
	
	@Column(name="LP_SizeBiz")
	private String sizeBiz;
	
	@Column(name="LP_Address")
	private String address;

	@Column(name="LP_PerInCharge")
	private String perInCharge;
	
	@Column(name="LP_Mobile")
	private String mobile;
	
	@Column(name="LP_Email")
	private String email;
	
	@Column(name="LP_URL")
	private String url;
	
	@Column(name="LP_Owner")
	private String owner;
	
	@Column(name="LP_Consultant")
	private String consultant;
	
	@Column(name="LP_Construction")
	private String construction;
	
	@Column(name="LP_MainConstruction")
	private String mainConstruction;
	
	@Column(name="LP_SubConstructor")
	private String subConstructor;
	
	@Column(name="LP_Remark")
	private String remark;
	
	@Column(name="LP_StartDate", columnDefinition="DATETIME")
	//@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Temporal(TemporalType.TIMESTAMP)
	private Date startDate;
	
	@Column(name="LP_endDate", columnDefinition="DATETIME")
	@Temporal(TemporalType.TIMESTAMP)
	//@Convert(converter = LocalDateTimePersistenceConverter.class)
	private Date endDate;
	
	@Transient
	private MeDataSource dataSource;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccountManager() {
		return accountManager;
	}

	public void setAccountManager(String accountManager) {
		this.accountManager = accountManager;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTypeBiz() {
		return typeBiz;
	}

	public void setTypeBiz(String typeBiz) {
		this.typeBiz = typeBiz;
	}

	public String getSizeBiz() {
		return sizeBiz;
	}

	public void setSizeBiz(String sizeBiz) {
		this.sizeBiz = sizeBiz;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPerInCharge() {
		return perInCharge;
	}

	public void setPerInCharge(String perInCharge) {
		this.perInCharge = perInCharge;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getConsultant() {
		return consultant;
	}

	public void setConsultant(String consultant) {
		this.consultant = consultant;
	}

	public String getConstruction() {
		return construction;
	}

	public void setConstruction(String construction) {
		this.construction = construction;
	} 

	public String getMainConstruction() {
		return mainConstruction;
	}

	public void setMainConstruction(String mainConstruction) {
		this.mainConstruction = mainConstruction;
	}

	public String getSubConstructor() {
		return subConstructor;
	}

	public void setSubConstructor(String subConstructor) {
		this.subConstructor = subConstructor;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public MeDataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}
}
