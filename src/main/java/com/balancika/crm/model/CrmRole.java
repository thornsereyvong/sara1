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
import javax.persistence.OneToMany;
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
@Table(name = "crm_role")
public class CrmRole implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="RM_ID", unique = true, nullable = false, length = 11)
	@NotEmpty
	private String roleId;
	
	@NotEmpty
	@Column(name="RM_Name", nullable = false)
	private String roleName;
	
	@Column(name="RM_Des")
	private String description;
	
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="RM_RoleID")
	@Fetch(FetchMode.SUBSELECT)
	private List<CrmRoleDetail> roleDetails;

	@Column(name="RM_CBy", updatable = false)
	private String createBy;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="RM_CDate", updatable  = false)
	private LocalDateTime createDate;
	
	@Column(name="RM_MBy")
	private String modifyBy;
	
	@Convert(converter = LocalDateTimePersistenceConverter.class)
	@Column(name="RM_MDate", updatable = false, insertable = false)
	private LocalDateTime modifyDate;
	
	@Column(name="RM_Status", nullable = false)
	private int roleStatus;
	
	@Transient
	private MeDataSource meDataSource;
	 
	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public String getModifyBy() {
		return modifyBy;
	}

	public void setModifyBy(String modifyBy) {
		this.modifyBy = modifyBy;
	}

	public LocalDateTime getModifyDate() {
		return modifyDate;
	}

	public void setModifyDate(LocalDateTime modifyDate) {
		this.modifyDate = modifyDate;
	}

	public List<CrmRoleDetail> getRoleDetails() {
		return roleDetails;
	}

	public void setRoleDetails(List<CrmRoleDetail> roleDetails) {
		this.roleDetails = roleDetails;
	}

	public int getRoleStatus() {
		return roleStatus;
	}

	public void setRoleStatus(int roleStatus) {
		this.roleStatus = roleStatus;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}
}
