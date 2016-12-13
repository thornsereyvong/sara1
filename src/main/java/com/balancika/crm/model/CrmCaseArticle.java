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

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

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
	
	@ManyToOne(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="A_ItemID")
	private AmeItem item;
	
	@Column(name="A_Description")
	private String articleDes;
	
	
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
	
	@Transient
	private String convertModifiedDate;
	
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

	public AmeItem getItem() {
		return item;
	}

	public void setItem(AmeItem item) {
		this.item = item;
	}

	public String getArticleDes() {
		return articleDes;
	}

	public void setArticleDes(String articleDes) {
		this.articleDes = articleDes;
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

	public String getConvertModifiedDate() {
		return convertModifiedDate;
	}

	public void setConvertModifiedDate(String convertModifiedDate) {
		this.convertModifiedDate = convertModifiedDate;
	}
	
}
