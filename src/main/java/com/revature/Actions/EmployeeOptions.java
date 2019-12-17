package com.revature.Actions;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bankingapplication.Bank;
import com.revature.bankingapplication.Customer;

public class EmployeeOptions {
	public void employeeActions() {
		
		Bank employeeBank = new Bank();
		String employeePassword="Revature";
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Password confirmation required.");
		System.out.println("Password:");
		String passwordAttempt = scanner.nextLine();
		
		if(passwordAttempt.equals(employeePassword)) {
		System.out.println("Access granted");
		System.out.println("");
		
		System.out.println("1. View all accounts");
		System.out.println("2. View single account");
		System.out.println("3. Approve accounts");
		System.out.println("4. Exit application");
		int choice = Integer.parseInt(scanner.nextLine());
		int acc_ID;
		
		switch(choice) {
		case 1:
			ArrayList<Customer> allAccounts = employeeBank.getAllAccounts();
			System.out.println(allAccounts);
			break;
		case 2:
			System.out.println("Enter the account ID of the account to be viewed");
			acc_ID = Integer.parseInt(scanner.nextLine());
			employeeBank.getUser(acc_ID);
			employeeBank.getAccount(acc_ID);
			System.out.println(employeeBank.getAccount(acc_ID));
			break;
		case 3:
			System.out.println("Enter the ID of the account that you would like to approve");
			acc_ID = Integer.parseInt(scanner.nextLine());
			System.out.println("Enter '1' if you would like to approve the account");
			System.out.println("Enter '2' if you would like to decline the approval of the account");
			int decision = Integer.parseInt(scanner.nextLine());
			String status;
			switch (decision) {
			case 1:
				status = "Active";
				employeeBank.updateStatus(acc_ID, status);
				System.out.println("Account approved");
				break;
			case 2:
				status = "Declined";
				employeeBank.updateStatus(acc_ID, status);
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
	}else {System.out.println("");	
	System.out.println("Incorrect Password");
	}
}
}
