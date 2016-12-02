package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name="crm_case_article")
@DynamicInsert
@DynamicUpdate
public class CrmCaseArticle implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "A_ID")
	private String articleId;
	
	@Column(name="A_Title")
	private String atricleTitle;
	
	@Column(name="A_Key")
	private String articleKey;
	
	@Column(name="A_CaseID")
	private String articleCaseId;
	
	@Column(name="A_Description")
	private String articleDes;
	
	@Column(name="A_ItemID")
	private String articleItemId;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="A_CDate")
	private LocalDateTime articleCreateDate;
	
	@Transient
	private String convertCreateDate;
	
	@Column(name="A_CBy")
	private String articleCreateBy;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="A_MDate")
	private LocalDateTime atricleModifiedDate;
	
	@Column(name="A_MBy")
	private String articleModifiedBy;
	
	@Transient
	private MeDataSource meDataSource;

	public String getArticleId() {
		return articleId;
	}

	public void setArticleId(String articleId) {
		this.articleId = articleId;
	}

	public String getAtricleTitle() {
		return atricleTitle;
	}

	public void setAtricleTitle(String atricleTitle) {
		this.atricleTitle = atricleTitle;
	}

	public String getArticleKey() {
		return articleKey;
	}

	public void setArticleKey(String articleKey) {
		this.articleKey = articleKey;
	}

	public String getArticleCaseId() {
		return articleCaseId;
	}

	public void setArticleCaseId(String articleCaseId) {
		this.articleCaseId = articleCaseId;
	}

	public String getArticleDes() {
		return articleDes;
	}

	public void setArticleDes(String articleDes) {
		this.articleDes = articleDes;
	}

	public String getArticleItemId() {
		return articleItemId;
	}

	public void setArticleItemId(String articleItemId) {
		this.articleItemId = articleItemId;
	}

	public LocalDateTime getArticleCreateDate() {
		return articleCreateDate;
	}

	public void setArticleCreateDate(LocalDateTime articleCreateDate) {
		this.articleCreateDate = articleCreateDate;
	}

	public String getConvertCreateDate() {
		return convertCreateDate;
	}

	public void setConvertCreateDate(String convertCreateDate) {
		this.convertCreateDate = convertCreateDate;
	}

	public String getArticleCreateBy() {
		return articleCreateBy;
	}

	public void setArticleCreateBy(String articleCreateBy) {
		this.articleCreateBy = articleCreateBy;
	}

	public LocalDateTime getAtricleModifiedDate() {
		return atricleModifiedDate;
	}

	public void setAtricleModifiedDate(LocalDateTime atricleModifiedDate) {
		this.atricleModifiedDate = atricleModifiedDate;
	}

	public String getArticleModifiedBy() {
		return articleModifiedBy;
	}

	public void setArticleModifiedBy(String articleModifiedBy) {
		this.articleModifiedBy = articleModifiedBy;
	}

	public MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
