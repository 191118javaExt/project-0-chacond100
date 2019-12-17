package com.revature.Actions;

import java.util.Scanner;
import com.revature.bankingapplication.*;

public class CustomerOptions {
	
	public void newCustomerActions() {
	@SuppressWarnings("resource")
	Scanner scanner = new Scanner(System.in);
	Bank customerBank = new Bank();
	String firstName;
	String lastName;
	String username;
	String password;
	String status = "Pending";
	AccountType accountType;
	double balance = 0;
	
	System.out.println("State your first name");
	firstName = scanner.nextLine();
	
	System.out.println("State your last name");
	lastName = scanner.nextLine();
	
	System.out.println("Create a valid username");
	username = scanner.nextLine();
	
	System.out.println("Create valid password");
	password = scanner.nextLine();
	
	System.out.println("What type of account you would like to create today");
	System.out.println("1. Checking");
	System.out.println("2. Savings");
	
	int choice = Integer.parseInt(scanner.nextLine());
	
	switch (choice) {
	case 1:
		accountType = AccountType.Checking;
		break;
	case 2:
		accountType = AccountType.Savings;
		break;
	default:
		accountType = AccountType.Undefined;
		break;
	}
	customerBank.addAccount(firstName, lastName, username, password, accountType, balance, status);
	System.out.println("Please wait 2-3 business days for your accounts to be approved or denied");
	}
}
