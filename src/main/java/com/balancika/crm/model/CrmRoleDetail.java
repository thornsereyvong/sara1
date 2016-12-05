package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="crm_role_detail")
public class CrmRoleDetail implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="RM_DetailID", nullable = false, unique = true)
	private int roleDetailId;
	
	@OneToOne
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name = "RM_ModuleID", nullable = false)
	private CrmModule module;
	
	@Column(name = "RM_Access")
	private String roleAccess;
	
	@Column(name = "RM_Delete")
	private String roleDelete;
	
	@Column(name = "RM_Edit")
	private String roleEdit;
	
	@Column(name = "RM_Export")
	private String roleExport;
	
	@Column(name = "RM_Import")
	private String roleImport;
	
	@Column(name = "RM_List")
	private String roleList;
	
	@Column(name = "RM_View")
	private String roleView;
	
	/*@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name="RM_RoleID", nullable = true)
	private CrmRole role;*/

	@Transient
	private MeDataSource meDataSource;

	public CrmModule getModule() {
		return module;
	}

	public void setModule(CrmModule module) {
		this.module = module;
	}
	
	public String getRoleAccess() {
		return roleAccess;
	}

	public void setRoleAccess(String roleAccess) {
		this.roleAccess = roleAccess;
	}

	public String getRoleDelete() {
		return roleDelete;
	}

	public void setRoleDelete(String roleDelete) {
		this.roleDelete = roleDelete;
	}

	public String getRoleEdit() {
		return roleEdit;
	}

	public void setRoleEdit(String roleEdit) {
		this.roleEdit = roleEdit;
	}

	public String getRoleExport() {
		return roleExport;
	}

	public void setRoleExport(String roleExport) {
		this.roleExport = roleExport;
	}

	public String getRoleImport() {
		return roleImport;
	}

	public void setRoleImport(String roleImport) {
		this.roleImport = roleImport;
	}

	public String getRoleList() {
		return roleList;
	}

	public void setRoleList(String roleList) {
		this.roleList = roleList;
	}

	public String getRoleView() {
		return roleView;
	}

	public void setRoleView(String roleView) {
		this.roleView = roleView;
	}

	public int getRoleDetailId() {
		return roleDetailId;
	}

	public void setRoleDetailId(int roleDetailId) {
		this.roleDetailId = roleDetailId;
	}

	public final MeDataSource getMeDataSource() {
		return meDataSource;
	}

	public final void setMeDataSource(MeDataSource meDataSource) {
		this.meDataSource = meDataSource;
	}

	/*public CrmRole getRole() {
		return role;
	}

	public void setRole(CrmRole role) {
		this.role = role;
	}*/
	
	
}
