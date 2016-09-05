package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="crm_case_priority")
public class CrmCasePriority implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="CSP_ID", nullable = false)
	private int priorityId;
	
	@NotEmpty
	@Column(name="CSP_Name", nullable = false)
	private String priorityName;
	
	@Column(name="CSP_Des")
	private String priorityDes;

	public int getPriorityId() {
		return priorityId;
	}

	public void setPriorityId(int priorityId) {
		this.priorityId = priorityId;
	}

	public String getPriorityName() {
		return priorityName;
	}

	public void setPriorityName(String priorityName) {
		this.priorityName = priorityName;
	}

	public String getPriorityDes() {
		return priorityDes;
	}

	public void setPriorityDes(String priorityDes) {
		this.priorityDes = priorityDes;
	}

}
