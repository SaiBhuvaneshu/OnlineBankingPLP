package com.spring.obs.dao;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import org.springframework.stereotype.Repository;

import com.spring.obs.model.AccountMaster;
import com.spring.obs.model.FundTransfer;
import com.spring.obs.model.Payee;
import com.spring.obs.model.Transactions;
import com.spring.obs.model.User;

@Repository
public class FundDAOImpl implements FundDAO {

	@PersistenceContext
	private EntityManager manager;

	@Override
	public boolean checkAccountExists(long accId) {
		AccountMaster accMaster = manager.find(AccountMaster.class, accId);
		if (accMaster != null)
			return true;
		else
			return false;
	}

	@Override
	public List<Long> getOwnMultpileAccIds(long accountId) {
		TypedQuery<User> userQuery = manager.createQuery(
				"select t from User t where account_Id=:accntId", User.class).setParameter("accntId", accountId);
		List<User> user = userQuery.getResultList();
		
		TypedQuery<Long> query = (TypedQuery<Long>) manager
				.createQuery("select u.accountId from User u where u.userId= :userid",Long.class);
		query.setParameter("userid", user.get(0).getUserId());		
		System.out.println(user.get(0).getUserId());
		List<Long> OwnPayeeAccIdsList = query.getResultList();
		return OwnPayeeAccIdsList;
	}
	
	@Override
	public boolean checkTransactionPassword(User userTransactionPwd,
			long pid, long l) {

		TypedQuery<User> query = manager.createQuery(
				"select u from User u where account_Id=:accntId",
				User.class).setParameter("accntId", l);
		User user = query.getSingleResult();
		if (userTransactionPwd.getTransactionPassword().equals(
				user.getTransactionPassword()))
			return true;
		else
			return false;

	}

	@Override
	public boolean checkSufficientAccountBal(AccountMaster amount, long l) {

		System.out.println("checkaccbal dao");
		TypedQuery<AccountMaster> query = manager.createQuery(
				"select a from AccountMaster a where account_Id=:accntId",
				AccountMaster.class).setParameter("accntId", l);
		AccountMaster accountBal = query.getSingleResult();
		
		System.out.println("curr bal: "+accountBal.getAccountBalance());
		System.out.println("user entered bal :"+amount.getAccountBalance());
		
		if(accountBal.getAccountBalance() >= amount.getAccountBalance())
			return true;
		else
			return false;
		
	}

	@Override
	public void updateFund(long l, AccountMaster amount, long payeeId) {

//		fid++;
			//System.out.println("fid:"+fid);
			LocalDate today = LocalDate.now();
	/*		fundtransfer_id,*/
		/*	Query query = manager
					.createNativeQuery("insert into fund_transfer(account_id,payee_account_id,date_of_transfer,transfer_amount)"
							+ "values(?,?,?,?)");
			//query.setParameter(1, FUNDTRANSFER_ID_SEQ);
			query.setParameter(1, l);
			query.setParameter(2, payeeId);
			query.setParameter(3,Date.valueOf(today));
			query.setParameter(4, amount.getAccountBalance());
			query.executeUpdate();*/
			
			FundTransfer fundTransfer = new FundTransfer();
			fundTransfer.setAccountId(l);
			fundTransfer.setPayeeAccountId(payeeId);
			fundTransfer.setTransferAmount((int)amount.getAccountBalance());
			fundTransfer.setDateOfTransfer(Date.valueOf(today));
			manager.persist(fundTransfer);
			
			//To get current balance
			TypedQuery<AccountMaster> currentBal = manager.createQuery(
					"select a from AccountMaster a where account_Id=:accntId",
					AccountMaster.class).setParameter("accntId", l);
			AccountMaster accountBal = currentBal.getSingleResult();
			
			
			
			//To get current balance of payee
			TypedQuery<AccountMaster> currentBalPayee = manager.createQuery(
					"select a from AccountMaster a where account_Id=:payeeAccntId",
					AccountMaster.class).setParameter("payeeAccntId", payeeId);
			AccountMaster payeeAccountBal = currentBal.getSingleResult();
			
			
			
			//To update account balance of from account
			Query update = manager.createQuery("update AccountMaster set account_balance = :debitbal where account_id = :accntId");
			long debitbal = accountBal.getAccountBalance() - amount.getAccountBalance();
			
			update.setParameter("debitbal", debitbal);
			update.setParameter("accntId",l);
			update.executeUpdate();
			
			
			//to update account balance of to account
			Query update2 = manager.createQuery("update AccountMaster set account_balance = :creditbal where account_id = :payeeId");
			long creditbal = payeeAccountBal.getAccountBalance() + amount.getAccountBalance();
					
			update2.setParameter("creditbal", creditbal);
			update2.setParameter("payeeId",payeeId);
			update2.executeUpdate();
	}

	//---------------------------------------------------------------------------------------------------------

	@Override
	public boolean checkNotMultipleAccount(Payee payee) {

		TypedQuery<User> userQuery = (TypedQuery<User>) manager.createQuery(
				"select u from User u where u.accountId= :accId", User.class);
		userQuery.setParameter("accId", payee.getAccountId());
		User user = userQuery.getSingleResult();
		TypedQuery<User> query = (TypedQuery<User>) manager
				.createQuery("select u from User u where u.userId= :userid and u.accountId= :accid",User.class);
		query.setParameter("userid", user.getUserId());
		query.setParameter("accid", payee.getPayeeAccountId());
		System.out.println("USerId: "+user.getUserId());
		System.out.println("Payee Accc:" + payee.getPayeeAccountId());
		List<User> userList = new ArrayList<User>();
	    userList = query.getResultList();
		if (userList.size() == 0) {
			System.out.println("ekfd");
			return true;
		} else {
			
			return false;
		}
	}

	@Override
	public boolean isRegisteredPayee(Payee payee) {
		System.out.println("Payee Not Registered");
		TypedQuery<Payee> query = (TypedQuery<Payee>) manager
				.createQuery(
						"select p from Payee p where p.accountId= :accId and p.payeeAccountId= :payeeAccId",
						Payee.class);

		query.setParameter("accId", payee.getAccountId());
		query.setParameter("payeeAccId", payee.getPayeeAccountId());
		List<Payee> payeeList = new ArrayList<Payee>();
		 payeeList = query.getResultList();

		if (payeeList.size() == 0) {

			return false;
		} else {
			System.out.println("Payee Registered");
			return true;
		}
	}

	@Override
	public boolean isValidPayeeDetails(Payee payee) {
        
		TypedQuery<Payee> query = (TypedQuery<Payee>) manager
				.createQuery(
						"select p from Payee p where p.accountId= :accId and p.payeeAccountId= :payeeAccId and p.nickName= :nickname",
						Payee.class);

		query.setParameter("accId", payee.getAccountId());
		query.setParameter("payeeAccId", payee.getPayeeAccountId());
		query.setParameter("nickname", payee.getNickName());
		List<Payee> payeeList = new ArrayList<Payee>();
		 payeeList = query.getResultList();
		 if (payeeList.size() == 0) {

				return false;
			} else {
				
				return true;
			}
	}

	@Override
	public void registerPayee(Payee payee) {
		
			manager.persist(payee);
	}

	@Override
	public boolean checkTransAmount(Transactions transactionDetails) {

		TypedQuery<AccountMaster> query = (TypedQuery<AccountMaster>) manager
				.createQuery(
						"select a from AccountMaster a where a.accountId= :accId",AccountMaster.class);
		query.setParameter("accId", transactionDetails.getAccountId());
		AccountMaster account = query.getSingleResult();
		if(account.getAccountBalance() >= transactionDetails.getTranAmount())
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isValidTransactionPassword(User user) {
            
		TypedQuery<User> query = (TypedQuery<User>) manager
				.createQuery("select u from User u where u.transactionPassword= :transPass and u.accountId= :accid",User.class);
		query.setParameter("transPass", user.getTransactionPassword());
		query.setParameter("accid", user.getAccountId());
		List<User> userList = new ArrayList<User>();
		userList = query.getResultList();
		if(userList.size() == 0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

	@Override
	public void addTransaction(Transactions transaction,long payeeAccountId) {

		Transactions debit = new Transactions();
		LocalDate today = LocalDate.now();
		debit.setAccountId(transaction.getAccountId());
		debit.setTranDescription(transaction.getTranDescription());
		debit.setTranAmount(transaction.getTranAmount());
		debit.setTransactionType('D');
		debit.setDateOfTransaction(Date.valueOf(today));
		Transactions credit = new Transactions();
		credit.setAccountId(payeeAccountId);
		credit.setTranDescription(transaction.getTranDescription());
		credit.setTranAmount(transaction.getTranAmount());
		credit.setTransactionType('C');
		credit.setDateOfTransaction(Date.valueOf(today));
		manager.persist(debit);
		System.out.println("DEBIT PERSISTED");
		manager.persist(credit);
        System.out.println("CREDIT PERSISTED");
	
		FundTransfer fundTransfer = new FundTransfer();
		fundTransfer.setAccountId(transaction.getAccountId());
		fundTransfer.setPayeeAccountId(payeeAccountId);
		fundTransfer.setTransferAmount((int)transaction.getTranAmount());
		fundTransfer.setDateOfTransfer(Date.valueOf(today));
		manager.persist(fundTransfer);
		System.out.println("FUNDTRANSFER PERSISTED");
		
		AccountMaster sender = manager.find(AccountMaster.class, transaction.getAccountId());
		AccountMaster receiver = manager.find(AccountMaster.class, payeeAccountId);
		int senderAccountBalance = sender.getAccountBalance();
		int receiverAccountBalance = receiver.getAccountBalance();
		int senderFinalBalance = senderAccountBalance - (int)transaction.getTranAmount();
		int receiverFinalBalance = receiverAccountBalance + (int)transaction.getTranAmount();
		sender.setAccountBalance(senderFinalBalance);
		receiver.setAccountBalance(receiverFinalBalance);
		manager.merge(sender);
		System.out.println("SENDER MERGED");
		manager.merge(receiver);
		System.out.println("RECIEVER MERGED");
	}
	
	@Override
	public List<Payee> retrieveAllPayeesByAccId(long accountId) {
		
		TypedQuery<Payee> query = manager.createQuery(
				"select t from Payee t where t.accountId=:accntId", Payee.class)
				.setParameter("accntId", accountId);

		List<Payee> list = query.getResultList();
		return list;
	}

	
}
