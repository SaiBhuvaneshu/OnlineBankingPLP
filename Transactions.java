package com.spring.obs.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="transactions")
public class Transactions {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="Transaction_Id")
	private int transactionId;
	
	@Column(name="Tran_Description")
	private String tranDescription;
	
	@Column(name="DateOfTransaction")
	private Date dateOfTransaction;
	
	@Column(name="TransactionType")
	private char transactionType;
	
	@Column(name="TranAmount")
	private long tranAmount;
	
	@Column(name="Account_Id")
	private long accountId;
	
/*	@ManyToOne(fetch = FetchType.EAGER)*/
	
	/*private AccountMaster accMaster;*/

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}

	public String getTranDescription() {
		return tranDescription;
	}

	public void setTranDescription(String tranDescription) {
		this.tranDescription = tranDescription;
	}

	public Date getDateOfTransaction() {
		return dateOfTransaction;
	}

	public void setDateOfTransaction(Date dateOfTransaction) {
		this.dateOfTransaction = dateOfTransaction;
	}

	public char getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(char transactionType) {
		this.transactionType = transactionType;
	}

	public long getTranAmount() {
		return tranAmount;
	}

	public void setTranAmount(long tranAmount) {
		this.tranAmount = tranAmount;
	}

	

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	/*public AccountMaster getAccMaster() {
		return accMaster;
	}

	public void setAccMaster(AccountMaster accMaster) {
		this.accMaster = accMaster;
	}*/
	
	

}
