package com.spring.obs.model;

import java.io.Serializable;

import javax.persistence.*;

import java.math.BigDecimal;
import java.util.Date;


/**
 * The persistent class for the FUND_TRANSFER database table.
 * 
 */
@Entity
@Table(name="FUND_TRANSFER")
@NamedQuery(name="FundTransfer.findAll", query="SELECT f FROM FundTransfer f")
public class FundTransfer implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="FUNDTRANSFER_ID")
	private long fundtransferId;

	@Column(name="DATE_OF_TRANSFER")
	private Date dateOfTransfer;

	@Column(name="PAYEE_ACCOUNT_ID")
	private long payeeAccountId;

	@Column(name="TRANSFER_AMOUNT")
	private int transferAmount;
	
	@Column(name="ACCOUNT_ID")
	private long accountId;

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public FundTransfer() {
	}

	public long getFundtransferId() {
		return this.fundtransferId;
	}

	public void setFundtransferId(long fundtransferId) {
		this.fundtransferId = fundtransferId;
	}

	public Date getDateOfTransfer() {
		return this.dateOfTransfer;
	}

	public void setDateOfTransfer(Date dateOfTransfer) {
		this.dateOfTransfer = dateOfTransfer;
	}

	public long getPayeeAccountId() {
		return this.payeeAccountId;
	}

	public void setPayeeAccountId(long payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}

	public int getTransferAmount() {
		return this.transferAmount;
	}

	public void setTransferAmount(int transferAmount) {
		this.transferAmount = transferAmount;
	}

}