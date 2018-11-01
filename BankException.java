package com.capgemini.obs.exception;

import org.hibernate.metamodel.domain.Superclass;


public class BankException extends Exception{
 
	public BankException(String message){
		super(message);
	}
}
