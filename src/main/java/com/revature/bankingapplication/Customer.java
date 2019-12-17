package com.revature.bankingapplication;

public class Customer {
    private final Account account;
    

    Customer(Account account) {
        this.account = account;
    }
    
    @Override
    public String toString(){
        return "\nCustomer Information\n" +
                account;
    }
    
    Account getAccount(){
        return account;
    }
}