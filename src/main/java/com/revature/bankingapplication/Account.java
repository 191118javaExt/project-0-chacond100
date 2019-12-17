package com.revature.bankingapplication;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Account implements Serializable {
	private double balance = 0;
	private int accountNumber;
	private String status;
	
	Account (int accountID){
		accountNumber=accountID;
	}
	
	public abstract AccountType getAccountType();
    
    public double getBalance() {
        return balance;
    }
   
    public final void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }
    
    void setAccountNumber(int accountNumber) {
    	this.accountNumber = accountNumber;
    }
    
    @Override
    public String toString() {
    	return "Account Type: " + getAccountType().name() + " Account\n" +
    	       "Account Number: " + this.getAccountNumber() + "\n" +
    	       "Balance: " + this.getBalance() + "\n"+
    	       "Status: " + this.getStatus() ;
    }

	
    public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}
