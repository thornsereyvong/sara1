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
@Table(name="crm_account_type")
public class CrmAccountType implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="AT_ID")
	private int accountID;
	
	@NotEmpty
	@Column(name="AT_Name", nullable = false)
	private String accountName;
	
	@Column(name="AT_Des")
	private String accountDes;

	public int getAccountID() {
		return accountID;
	}

	public void setAccountID(int accountID) {
		this.accountID = accountID;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountDes() {
		return accountDes;
	}

	public void setAccountDes(String accountDes) {
		this.accountDes = accountDes;
	}
}
