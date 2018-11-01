package com.spring.obs.service;

import java.util.List;

import com.spring.obs.model.AccountMaster;
import com.spring.obs.model.Payee;
import com.spring.obs.model.Transactions;
import com.spring.obs.model.User;

public interface FundService {
	
	public boolean checkAccountExists(long accId);

	public List<Long> getOwnMultpileAccIds(long accountId);
	
	public boolean checkTransactionPassword(User userTransactionPwd, long pid,long l);

	public boolean checkSufficientAccountBal(AccountMaster amount, long l);

	public void updateFund(long l, AccountMaster amount, long payeeId);	
	
	//------------------------------------------------------------------------------------------------
	
	public boolean checkNotMultipleAccount(Payee payee);

	public boolean isRegisteredPayee(Payee payee);

	public boolean isValidPayeeDetails(Payee payee);

	public void registerPayee(Payee payee);

	public boolean checkTransAmount(Transactions transactionDetails);

	public boolean isValidTransactionPassword(User user);

	public void addTransaction(Transactions transaction,long payeeAccountId);
	
	public List<Payee> retrieveAllPayeesByAccId(long accountId);
	
}
