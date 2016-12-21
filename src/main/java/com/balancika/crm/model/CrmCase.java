package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.SelectBeforeUpdate;
import org.hibernate.validator.constraints.NotEmpty;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity(name="crmCase")
@Table(name="crm_case")
@DynamicInsert(value = true)
@DynamicUpdate(value = true)
@SelectBeforeUpdate(value = true)
public class CrmCase implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@NotEmpty
	@Column(name="CS_ID", nullable = false)
	private String caseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_StatusID", nullable = false)
	private CrmCaseStatus status;

	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_TypeID", nullable = false)
	private CrmCaseType type;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "CS_Priority", nullable = false)
	private CrmCasePriority priority;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_CustID", nullable = true)
	private CrmCustomer customer;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_ContactID", nullable = true)
	private CrmContact contact;
	
	@Column(name="CS_Subject", unique = true)
	private String subject;
	
	@Column(name="CS_Des")
	private String des;
	
	@Column(name="CS_Resolution", updatable = false)
	private String resolution;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_ATo")
	private CrmUser assignTo;
	
	@Column(name="CS_CBy", updatable = false)
	private String createBy;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="CS_CDate", updatable = false)
	private LocalDateTime createDate;
	
	@Transient
	private String convertCreateDate;
	
	@Column(name="CS_MBy")
	private String modifyBy;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="CS_MDate", insertable = false, updatable = false)
	private Date modifyDate;
	
	@Column(name="CS_RosolvedBy", updatable = false)
	private String resolvedBy;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="CS_ResolvedDate", updatable = false)
	private LocalDateTime resolvedDate;
	
	@Transient
	private String convertResolvedDate;
	
	@Column(name="CS_EscalateTo", updatable = false)
	private String escalateTo;
	
	@Column(name="CS_EscalateStatus", updatable = false)
	private String escalateStatus;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="CS_FollowupDate")
	private LocalDateTime followupDate;
	
	@Transient
	private String convertFollowupDate;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="CS_ElapsedTime", updatable = false)
	private LocalDateTime elapsedTime;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_PriceCode")
	private PriceCode priceCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_Class")
	private AmeClass ameClass;
	
	@Column(name="CS_TotalSTax", columnDefinition = "double default 0")
	private double totalSTax;
	
	@Column(name="CS_TotalVTax", columnDefinition = "double default 0")
	private double totalVTax;
	
	@Column(name="CS_TotalAmt", columnDefinition = "double default 0")
	private double totalAmt;
	
	@Column(name="CS_InvDisDol", columnDefinition = "double default 0")
	private double invDisDol;
	
	@Column(name="CS_InvDisPer", columnDefinition = "double default 0")
	private double invDisPer;
	
	@Column(name="CS_TotalDis", columnDefinition = "double default 0")
	private double totalDis;
	
	@Column(name="CS_NetTotalAmt", columnDefinition = "double default 0")
	private double netTotalAmt;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_OriginID")
	private CrmCaseOrigin origin;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_ItemID")
	private AmeItem item;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_ArticleID")
	private CrmCaseArticle article;
	
	@Column(name="CS_Key")
	private String key;
	
	@OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_ID")
	private List<CrmCaseDetail> details;
	
	@Transient
	private MeDataSource meDataSource;

	
	
	
	
	public CrmCaseArticle getArticle() {
		return article;
	}

	public void setArticle(CrmCaseArticle article) {
		this.article = article;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getCaseId() {
		return caseId;
	}

	public void setCaseId(String caseId) {
		this.caseId = caseId;
	}

	public CrmCaseStatus getStatus() {
		return status;
	}

	public void setStatus(CrmCaseStatus status) {
		this.status = status;
	}

	public CrmCaseType getType() {
		return type;
	}

	public void setType(CrmCaseType type) {
		this.type = type;
	}

	public CrmCasePriority getPriority() {
		return priority;
	}

	public void setPriority(CrmCasePriority priority) {
		this.priority = priority;
	}

	public CrmCustomer getCaseCustomer() {
		return customer;
	}

	public void setCustomer(CrmCustomer customer) {
		this.customer = customer;
	}

	public CrmContact getContact() {
		return contact;
	}

	public void setContact(CrmContact contact) {
		this.contact = contact;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
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

	public CrmCustomer getCustomer() {
		return customer;
	}

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public Date getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(Date modifyDate) {
		this.modifyDate = modifyDate;
	}

	public String getConvertCreateDate() {
		return convertCreateDate;
	}

	public void setConvertCreateDate(String convertCreateDate) {
		this.convertCreateDate = convertCreateDate;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}

	public String getResolvedBy() {
		return resolvedBy;
	}

	public void setResolvedBy(String resolvedBy) {
		this.resolvedBy = resolvedBy;
	}

	public LocalDateTime getResolvedDate() {
		return resolvedDate;
	}

	public void setResolvedDate(LocalDateTime resolvedDate) {
		this.resolvedDate = resolvedDate;
	}

	public String getConvertResolvedDate() {
		return convertResolvedDate;
	}

	public void setConvertResolvedDate(String convertResolvedDate) {
		this.convertResolvedDate = convertResolvedDate;
	}

	public String getEscalateTo() {
		return escalateTo;
	}

	public void setEscalateTo(String escalateTo) {
		this.escalateTo = escalateTo;
	}

	public String getEscalateStatus() {
		return escalateStatus;
	}

	public void setEscalateStatus(String escalateStatus) {
		this.escalateStatus = escalateStatus;
	}

	public LocalDateTime getFollowupDate() {
		return followupDate;
	}

	public void setFollowupDate(LocalDateTime followupDate) {
		this.followupDate = followupDate;
	}

	public String getConvertFollowupDate() {
		return convertFollowupDate;
	}

	public void setConvertFollowupDate(String convertFollowupDate) {
		this.convertFollowupDate = convertFollowupDate;
	}

	public LocalDateTime getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(LocalDateTime elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	public PriceCode getPriceCode() {
		return priceCode;
	}

	public void setPriceCode(PriceCode priceCode) {
		this.priceCode = priceCode;
	}

	public AmeClass getAmeClass() {
		return ameClass;
	}

	public void setAmeClass(AmeClass ameClass) {
		this.ameClass = ameClass;
	}

	public double getTotalSTax() {
		return totalSTax;
	}

	public void setTotalSTax(double totalSTax) {
		this.totalSTax = totalSTax;
	}

	public double getTotalVTax() {
		return totalVTax;
	}

	public void setTotalVTax(double totalVTax) {
		this.totalVTax = totalVTax;
	}

	public double getTotalAmt() {
		return totalAmt;
	}

	public void setTotalAmt(double totalAmt) {
		this.totalAmt = totalAmt;
	}

	public double getInvDisDol() {
		return invDisDol;
	}

	public void setInvDisDol(double invDisDol) {
		this.invDisDol = invDisDol;
	}

	public double getInvDisPer() {
		return invDisPer;
	}

	public void setInvDisPer(double invDisPer) {
		this.invDisPer = invDisPer;
	}

	public double getTotalDis() {
		return totalDis;
	}

	public void setTotalDis(double totalDis) {
		this.totalDis = totalDis;
	}

	public double getNetTotalAmt() {
		return netTotalAmt;
	}

	public void setNetTotalAmt(double netTotalAmt) {
		this.netTotalAmt = netTotalAmt;
	}

	public CrmCaseOrigin getOrigin() {
		return origin;
	}

	public void setOrigin(CrmCaseOrigin origin) {
		this.origin = origin;
	}

	public AmeItem getItem() {
		return item;
	}

	public void setItem(AmeItem item) {
		this.item = item;
	}

	public List<CrmCaseDetail> getDetails() {
		return details;
	}

	public void setDetails(List<CrmCaseDetail> details) {
		this.details = details;
	}
}
