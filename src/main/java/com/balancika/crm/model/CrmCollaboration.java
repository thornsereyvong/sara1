package com.balancika.crm.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.NotEmpty;

import com.balancika.crm.utilities.LocalDateTimePersistenceConverter;

@Entity
@Table(name = "crm_collaboration")
public class CrmCollaboration implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CB_ID", nullable = false)
	private int colId;
	
	@NotEmpty
	@Column(name = "CB_Des", nullable = false)
	private String colDes;
	
	@Column(name="CB_UName", nullable = false)
	private String colUser;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="collapId")
	@Fetch(FetchMode.JOIN)
	private Set<CrmCollaborationTags> tags;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="PostID")
	@Fetch(FetchMode.JOIN)
	@OrderBy(value = "CommentID ASC")
	private Set<CrmCollaborationDetails> details;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="CB_CreateDate")
	private LocalDateTime colCreateDate;
	
	@Column(name = "CB_RToId")
	private String colRelatedToModuleId;
	
	@Column(name = "CB_RToType")
	private String colRelatedToModuleName;
	
	@Column(name = "CB_ActivityType")
	private String colActivityType;
	
	@Column(name="CB_ActvityID")
	private String colActivityId;
	
	@Column(name = "CB_Own")
	private String colOwn;
	
	@Transient
	private String createDate;
	
	@Transient
	private int like;
	
	@Transient
	private boolean checkLike;
	
	@Transient
	private MeDataSource meDataSource;

	public int getColId() {
		return colId;
	}

	public void setColId(int colId) {
		this.colId = colId;
	}

	public String getColDes() {
		return colDes;
	}

	public void setColDes(String colDes) {
		this.colDes = colDes;
	}

	public String getColUser() {
		return colUser;
	}

	public void setColUser(String colUser) {
		this.colUser = colUser;
	}

	public LocalDateTime getColCreateDate() {
		return colCreateDate;
	}

	public void setColCreateDate(LocalDateTime colCreateDate) {
		this.colCreateDate = colCreateDate;
	}

	public Set<CrmCollaborationTags> getTags() {
		return tags;
	}

	public void setTags(Set<CrmCollaborationTags> tags) {
		this.tags = tags;
	}
	
	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}

	public String getColRelatedToModuleId() {
		return colRelatedToModuleId;
	}

	public void setColRelatedToModuleId(String colRelatedToModuleId) {
		this.colRelatedToModuleId = colRelatedToModuleId;
	}

	public String getColRelatedToModuleName() {
		return colRelatedToModuleName;
	}

	public void setColRelatedToModuleName(String colRelatedToModuleName) {
		this.colRelatedToModuleName = colRelatedToModuleName;
	}

	public String getColActivityType() {
		return colActivityType;
	}

	public void setColActivityType(String colActivityType) {
		this.colActivityType = colActivityType;
	}

	public String getColActivityId() {
		return colActivityId;
	}

	public void setColActivityId(String colActivityId) {
		this.colActivityId = colActivityId;
	}

	public String getColOwn() {
		return colOwn;
	}

	public void setColOwn(String colOwn) {
		this.colOwn = colOwn;
	}

	public Set<CrmCollaborationDetails> getDetails() {
		return details;
	}

	public void setDetails(Set<CrmCollaborationDetails> details) {
		this.details = details;
	}

	public int getLike() {
		return like;
	}

	public void setLike(int like) {
		this.like = like;
	}

	public boolean isCheckLike() {
		return checkLike;
	}

	public void setCheckLike(boolean checkLike) {
		this.checkLike = checkLike;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}	
}
