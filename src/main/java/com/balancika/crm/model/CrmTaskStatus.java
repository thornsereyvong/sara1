package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="crm_task_status")
public class CrmTaskStatus implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="TS_ID", unique = true, nullable = false)
	private int taskStatusId;
	
	@NotEmpty
	@Column(name="TS_Name", unique = true, nullable = false)
	private String taskStatusName;
	
	@Column(name="TS_Des")
	private String taskStatusDes;
	
	@Transient
	private MeDataSource dataSource;

	public int getTaskStatusId() {
		return taskStatusId;
	}

	public void setTaskStatusId(int taskStatusId) {
		this.taskStatusId = taskStatusId;
	}

	public String getTaskStatusName() {
		return taskStatusName;
	}

	public void setTaskStatusName(String taskStatusName) {
		this.taskStatusName = taskStatusName;
	}

	public String getTaskStatusDes() {
		return taskStatusDes;
	}

	public void setTaskStatusDes(String taskStatusDes) {
		this.taskStatusDes = taskStatusDes;
	}

	public final MeDataSource getDataSource() {
		return dataSource;
	}

	public final void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
