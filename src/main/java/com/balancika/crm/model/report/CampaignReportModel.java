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

@Entity(name="CampaignReportModel")
@Table(name="crm_camp")
public class CampaignReportModel implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="CA_ID")
	private String campId;
	
	@Column(name="CA_Name")
	private String campName;
	
	@OneToMany(fetch = FetchType.EAGER)
	@JoinColumn(name="LA_CA_ID", nullable = false)
	private List<LeadReportModel> leads;
	
	public String getCampId() {
		return campId;
	}

	public void setCampId(String campId) {
		this.campId = campId;
	}

	public String getCampName() {
		return campName;
	}

	public void setCampName(String campName) {
		this.campName = campName;
	}

	public List<LeadReportModel> getLeads() {
		return leads;
	}

	public void setLeads(List<LeadReportModel> leads) {
		this.leads = leads;
	}
}
