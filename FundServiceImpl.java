package com.spring.obs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.spring.obs.dao.FundDAO;
import com.spring.obs.model.AccountMaster;
import com.spring.obs.model.Payee;
import com.spring.obs.model.Transactions;
import com.spring.obs.model.User;

@Service
@Transactional
public class FundServiceImpl implements FundService {

	@Autowired
	FundDAO fundDao;
	
	@Override
	public boolean checkAccountExists(long accId) {
		return fundDao.checkAccountExists(accId);
	}

	@Override
	public List<Long> getOwnMultpileAccIds(long accountId) {
		List<Long> OwnPayeeAccIds = fundDao.getOwnMultpileAccIds(accountId);
		return OwnPayeeAccIds;
	}
	
	@Override
	public boolean checkTransactionPassword(User userTransactionPwd,long pid,long l) {
		return fundDao.checkTransactionPassword(userTransactionPwd,pid,l);
	}

	@Override
	public boolean checkSufficientAccountBal(AccountMaster amount,long l) {
		return fundDao.checkSufficientAccountBal(amount,l);
	}

	@Override
	public void updateFund(long l, AccountMaster amount, long payeeId) {
		 fundDao.updateFund(l,amount,payeeId);
	}


	//-----------------------------------------------------------------------------------------------------
	
	@Override
	public boolean checkNotMultipleAccount(Payee payee) {
		// TODO Auto-generated method stub
		return fundDao.checkNotMultipleAccount(payee);
	}

	@Override
	public boolean isRegisteredPayee(Payee payee) {
		// TODO Auto-generated method stub
		return fundDao.isRegisteredPayee(payee);
	}

	@Override
	public boolean isValidPayeeDetails(Payee payee) {
		// TODO Auto-generated method stub
		return fundDao.isValidPayeeDetails(payee);
	}

	@Override
	public void registerPayee(Payee payee) {
		
		 fundDao.registerPayee(payee);
	}

	@Override
	public boolean checkTransAmount(Transactions transactionDetails) {

		return fundDao.checkTransAmount(transactionDetails);
	}

	@Override
	public boolean isValidTransactionPassword(User user) {

		return fundDao.isValidTransactionPassword(user);
	}

	@Override
	public void addTransaction(Transactions transaction,long payeeAccountId) {
	
    
	          fundDao.addTransaction(transaction,payeeAccountId);	
	}
	
	@Override
	public List<Payee> retrieveAllPayeesByAccId(long accountId) {
		
		List<Payee> PayeeList = fundDao.retrieveAllPayeesByAccId(accountId);
		return PayeeList;
	}
	
	
}
