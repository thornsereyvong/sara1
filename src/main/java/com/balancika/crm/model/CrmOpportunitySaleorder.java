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
@Table(name = "crm_opportunity_saleorder")
public class CrmOpportunitySaleorder implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="L_ID")
	private int opSaleId;
	
	@NotEmpty
	@Column(name="OP_ID", nullable = false)
	private String opId;
	
	@NotEmpty
	@Column(name="S_O_ID", nullable = false)
	private String saleId;

	public int getOpSaleId() {
		return opSaleId;
	}

	public void setOpSaleId(int opSaleId) {
		this.opSaleId = opSaleId;
	}

	public String getOpId() {
		return opId;
	}

	public void setOpId(String opId) {
		this.opId = opId;
	}

	public String getSaleId() {
		return saleId;
	}

	public void setSaleId(String saleId) {
		this.saleId = saleId;
	}
}
