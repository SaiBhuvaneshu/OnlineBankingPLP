package com.spring.obs.model;

import java.io.Serializable;

import javax.persistence.*;
import javax.validation.constraints.Min;

import java.math.BigDecimal;


/**
 * The persistent class for the PAYEETABLE database table.
 * 
 */
@Entity
@Table(name="PAYEETABLE")
public class Payee implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="SR_NO")
	private long srNo;

	@Column(name="NICK_NAME")
	private String nickName;

	@Min(message="*Account must be five digit long", value = 10000)
	@Column(name="PAYEE_ACCOUNT_ID")
	private long payeeAccountId;

	@Column(name="ACCOUNT_ID")
	private long accountId;

	public Payee() {
	}

	public long getSrNo() {
		return this.srNo;
	}

	public void setSrNo(long srNo) {
		this.srNo = srNo;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public long getPayeeAccountId() {
		return this.payeeAccountId;
	}

	public void setPayeeAccountId(long payeeAccountId) {
		this.payeeAccountId = payeeAccountId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

}