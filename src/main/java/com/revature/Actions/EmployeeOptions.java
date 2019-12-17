package com.revature.Actions;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankingapplication.Bank;
import com.revature.bankingapplication.Customer;

public class EmployeeOptions {
	
	final static Logger logger = Logger.getLogger(EmployeeOptions.class);
	
	public void employeeActions() {
		
		Bank employeeBank = new Bank();
		String employeePassword="Revature";
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		try {
		System.out.println("Password confirmation required.");
		System.out.println("Password:");
		String passwordAttempt = scanner.nextLine();
		
		if(passwordAttempt.equals(employeePassword)) {
		logger.info("Succesful employee account verification");
		System.out.println("Access granted");
		System.out.println("");
		
		System.out.println("1. View all accounts");
		System.out.println("2. View single account");
		System.out.println("3. Approve accounts");
		System.out.println("4. Exit application");
		int choice = Integer.parseInt(scanner.nextLine());
		int accountID;
		
		switch(choice) {
		case 1:
			ArrayList<Customer> allAccounts = employeeBank.getAllAccounts();
			System.out.println(allAccounts);
			break;
		case 2:
			System.out.println("Enter the account ID of the account to be viewed");
			accountID = Integer.parseInt(scanner.nextLine());
			employeeBank.getUser(accountID);
			employeeBank.getAccount(accountID);
			logger.info("Succesfully viewed account "+accountID);
			System.out.println(employeeBank.getAccount(accountID));
			break;
		case 3:
			System.out.println("Enter the ID of the account that you would like to approve");
			accountID = Integer.parseInt(scanner.nextLine());
			System.out.println("Enter '1' if you would like to approve the account");
			System.out.println("Enter '2' if you would like to decline the approval of the account");
			int decision = Integer.parseInt(scanner.nextLine());
			String status;
			switch (decision) {
			case 1:
				status = "Active";
				employeeBank.updateStatus(accountID, status);
				System.out.println("Account approved");
				break;
			case 2:
				status = "Declined";
				employeeBank.updateStatus(accountID, status);
				System.out.println("Account not approved");
				break;
			default:
				System.out.println("Response not-valid");
				break;
			}
			break;
		case 4:
			System.out.println("Thank you for visiting David's Banking Application");
			System.exit(0);
		default:
			break;
		}
	}else {
	logger.info("Failed account verification");
	System.out.println("");	
	System.out.println("Incorrect Password");
	}
		}catch(Exception e) {
			System.out.println("Invalid input");
			logger.warn("Invalid input in employee menu");
		}
	}
}
