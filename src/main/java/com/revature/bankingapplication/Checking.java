package com.revature.bankingapplication;

@SuppressWarnings("serial")
public class Checking extends Account{
	
	Checking(int accountID, double initialDeposit, String status){
        super(accountID);
        this.setBalance(initialDeposit);
        this.setStatus(status);
    }

    @Override
    public AccountType getAccountType() {
        return AccountType.Checking;
    }
}
