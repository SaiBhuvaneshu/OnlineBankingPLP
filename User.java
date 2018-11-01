package com.spring.obs.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="user_table")
public class User {
	
	@Id
	@Column(name="SR_NO")
	private int srno;
	
	/*private AccountMaster accMaster;*/
	
	
	/*@ManyToOne(fetch = FetchType.EAGER)*/
	@Column(name="Account_Id"/*,insertable = false, updatable = false*/)
	private long accountId;
	
	
	@Column(name="user_id")
	private int userId;
	
	@Column(name="login_password",length=15)
	private String loginPassword;
	
	@Column(name="secret_question",length=35)
	private String secretQuestion;
	
	@Column(name="Transaction_password",length=30)
	@NotEmpty(message="please provide password")
	private String transactionPassword;
	
	@Column(name="lock_status",length=1)
	private char lockStatus;
	
	@Column(name="secret_answer",length=30)
	private String secretAnswer;

	public String getSecretAnswer() {
		return secretAnswer;
	}

	public void setSecretAnswer(String secretAnswer) {
		this.secretAnswer = secretAnswer;
	}

	public int getSrno() {
		return srno;
	}

	public void setSrno(int srno) {
		this.srno = srno;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

/*	public AccountMaster getAccMaster() {
		return accMaster;
	}

	public void setAccMaster(AccountMaster accMaster) {
		this.accMaster = accMaster;
	}*/

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getLoginPassword() {
		return loginPassword;
	}

	public void setLoginPassword(String loginPassword) {
		this.loginPassword = loginPassword;
	}

	public String getSecretQuestion() {
		return secretQuestion;
	}

	public void setSecretQuestion(String secretQuestion) {
		this.secretQuestion = secretQuestion;
	}


	public String getTransactionPassword() {
		return transactionPassword;
	}

	public void setTransactionPassword(String transactionPassword) {
		this.transactionPassword = transactionPassword;
	}

	public char getLockStatus() {
		return lockStatus;
	}

	public void setLockStatus(char lockStatus) {
		this.lockStatus = lockStatus;
	}
	
	
}
