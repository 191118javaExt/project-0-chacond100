package com.revature.bankingapplication;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Bank implements Serializable{
	
	private DBService database = new DBService();
	
	public int addAccount (String firstName, String lastName, String username, String password, AccountType accountType, double balance, String status) {
		return database.addAccount(firstName, lastName, username, password, accountType, balance, status);
	}
	
	public Customer getAccount(int accountID) {
		return database.getAccount(accountID);
	}
	
	public ArrayList<Customer> getAllAccounts(){
		return database.getAllAccounts();
	}
	
	public boolean deleteAccount(int accountID) {
		System.out.println("Account deleted");
		return database.deleteAccount(accountID);
	}
	
	public static double round(double value, int places) {
		if(places<0) {
			throw new IllegalArgumentException();
		}
		BigDecimal bd=new BigDecimal(value);
		bd=bd.setScale(places, RoundingMode.HALF_UP);
		return bd.doubleValue();
		}

	public void withdraw (int accountID, double amount) throws Exception{
		Customer customer = getAccount(accountID);
		double transactionFee = getTransactionFee(customer.getAccount().getAccountType());
		if(amount + transactionFee>customer.getAccount().getBalance()) {
			throw new Exception();
		}
		double newBalance = customer.getAccount().getBalance() - amount - transactionFee;
		database.updateAccount(accountID, newBalance);
	}

	public void deposit (int accountID, double amount) throws Exception {
        Customer customer = getAccount(accountID);
        double transactionFee = getTransactionFee(customer.getAccount().getAccountType());
        if (amount <= 0) {
            throw new Exception();
        }
        double newBalance = customer.getAccount().getBalance() + amount - transactionFee;
        database.updateAccount(accountID, newBalance);
    }
	
	public double getTransactionFee(AccountType accountType) {
		double transactionFee=0;
		switch(accountType) {
			case Checking:
			case Savings:
				transactionFee= 5;
				System.out.println("Transaction Fee = $5.00");
				break;
			case Undefined:
			default:
				transactionFee=0;
				break;
		}
		return transactionFee;
		}

	public String updateStatus(int accountID, String status){
		@SuppressWarnings("unused")
		Customer customer = getAccount(accountID);
		database.updateStatus(accountID, status);
		return status;
	}

	public String login(int accountID) {
		String userPassword = database.login(accountID);
		return userPassword;
	}
	
	public String getUser(int accountID) {
		String userInformation = database.getUser(accountID);
		System.out.println(userInformation);
		return userInformation;
	}
}
