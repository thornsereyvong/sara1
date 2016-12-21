package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity(name="crmCaseSolution")
@Table(name="crm_case")
public class CrmCaseSolution implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@NotEmpty
	@Column(name="CS_ID", nullable = false)
	private String caseId;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_StatusID", nullable = false)
	private CrmCaseStatus status;
	
	@Column(name="CS_RosolvedBy")
	private String resolvedBy;
	
	@Column(name="CS_Resolution")
	private String resolution;
	
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="CS_ResolvedDate")
	private LocalDateTime resolvedDate;
	
	@Transient
	private String convertResolvedDate;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="CS_FollowupDate")
	private LocalDateTime followupDate;
	
	@Transient
	private String convertFollowupDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="CS_ArticleID")
	private CrmCaseArticle article;
	
	@Transient
	private MeDataSource meDataSource;
	
	public String getResolution() {
		return resolution;
	}

	public void setResolution(String resolution) {
		this.resolution = resolution;
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

	public CrmCaseArticle getArticle() {
		return article;
	}

	public void setArticle(CrmCaseArticle article) {
		this.article = article;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
