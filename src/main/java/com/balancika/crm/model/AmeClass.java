package com.balancika.crm.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tblclass")
public class AmeClass implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="ClassID")
	private String classId;
	
	@Column(name="Des")
	private String des;
	
	@Column(name="Inactive")
	private short inactive;
	
	@Column(name="SortOr")
	private int sortOr;

	public String getClassId() {
		return classId;
	}

	public void setClassId(String classId) {
		this.classId = classId;
	}

	public String getDes() {
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public short getInactive() {
		return inactive;
	}

	public void setInactive(short inactive) {
		this.inactive = inactive;
	}

	public int getSortOr() {
		return sortOr;
	}

	public void setSortOr(int sortOr) {
		this.sortOr = sortOr;
	}
}
