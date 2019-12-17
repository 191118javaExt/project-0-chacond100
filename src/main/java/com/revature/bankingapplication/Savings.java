package com.revature.bankingapplication;

@SuppressWarnings("serial")
public class Savings extends Account{
	
	Savings(int accountID,  double initialDeposit, String status){
        super(accountID);
        this.setBalance(initialDeposit);
        this.setStatus(status);
    }
   
    @Override
    public AccountType getAccountType() {
        return AccountType.Savings;
    }
}
