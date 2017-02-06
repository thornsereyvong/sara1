package com.balancika.crm.model.report;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="tblcustomer")
public class CustomerReport implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CustID")
	private String custId;
	
	@Column(name="CustName")
	private String custName;
	
	@OneToMany(fetch = FetchType.LAZY)
	@Fetch(FetchMode.JOIN)
	@JoinColumn(name="OP_CustID", nullable = false, unique = true)
	private List<OpportunityModel> opportunities;

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public List<OpportunityModel> getOpportunities() {
		return opportunities;
	}

	public void setOpportunities(List<OpportunityModel> opportunities) {
		this.opportunities = opportunities;
	}
	
}
