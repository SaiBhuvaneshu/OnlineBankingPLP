package com.spring.obs.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.validator.constraints.Range;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the ACCOUNT_MASTER database table.
 * 
 */
@Entity
@Table(name="ACCOUNT_MASTER")
@NamedQuery(name="AccountMaster.findAll", query="SELECT a FROM AccountMaster a")
public class AccountMaster implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name="ACCOUNT_MASTER_ACCOUNTID_GENERATOR", sequenceName="SEQ_ACC_ID")
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="ACCOUNT_MASTER_ACCOUNTID_GENERATOR")
	@Column(name="ACCOUNT_ID")
	@Range(min=1000,max=1000000000,message="*The accountId must be greater than 1000")
	private long accountId;

	@Column(name="ACCOUNT_BALANCE")
	private int accountBalance;

	@Column(name="ACCOUNT_TYPE")
	private String accountType;

	@Column(name="OPEN_DATE")
	private Date openDate;

	
	public long getAccountId() {
		return this.accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public int getAccountBalance() {
		return this.accountBalance;
	}

	public void setAccountBalance(int accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getAccountType() {
		return this.accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public Date getOpenDate() {
		return this.openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

}