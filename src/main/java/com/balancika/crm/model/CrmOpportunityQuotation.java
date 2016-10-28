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
@Table(name="crm_opportunity_quote")
public class CrmOpportunityQuotation implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="L_ID")
	private int opQuoteId;
	
	@NotEmpty
	@Column(name="OP_ID", nullable = false)
	private String opId;
	
	@NotEmpty
	@Column(name="QuotID", nullable = false)
	private String quoteId;
	
	@Transient
	private MeDataSource dataSource;

	public int getOpQuoteId() {
		return opQuoteId;
	}

	public void setOpQuoteId(int opQuoteId) {
		this.opQuoteId = opQuoteId;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getQuoteId() {
		return quoteId;
	}

	public void setQuoteId(String quoteId) {
		this.quoteId = quoteId;
	}

	public final MeDataSource getDataSource() {
		return dataSource;
	}

	public final void setDataSource(MeDataSource dataSource) {
		this.dataSource = dataSource;
	}
}
