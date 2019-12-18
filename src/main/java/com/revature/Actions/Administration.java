package com.revature.Actions;

import java.util.ArrayList;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankingapplication.Bank;
import com.revature.bankingapplication.Customer;

public class Administration {
	
	final static Logger logger = Logger.getLogger(Administration.class);
	
	public void administrativeActions(){
		Bank adminBank = new Bank();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		try {
		String adminPassword = "Revature1";
		System.out.println("Password confirmation needed");
		System.out.println("Password:");
		String passwordAttempt = scanner.nextLine();
		if(passwordAttempt.equals(adminPassword)) {
		
		System.out.println("Access granted");
		System.out.println("");
		
		System.out.println("1. View all accounts");
		System.out.println("2. Delete single account");
		System.out.println("3. Make deposit into account");
		System.out.println("4. Make withdrawal from account");
		System.out.println("5. Approve account");
		System.out.println("6. View single account");
		System.out.println("7. Transfer funds");
		System.out.println("8. Exit application");
		
		int choice = Integer.parseInt(scanner.nextLine());
		int acc_ID;
		int amount;
		
		switch (choice) {
		case 1:
			ArrayList<Customer> allAccounts = adminBank.getAllAccounts();
			System.out.println(allAccounts);
			logger.info("Succesfully retrieved all accounts");
			break;
		case 2:
			System.out.println("Enter the ID of the account that you would like to delete");
			acc_ID = Integer.parseInt(scanner.nextLine());
			adminBank.deleteAccount(acc_ID);
			logger.info("Succesfully deleted account "+acc_ID);
			break;
		case 3:
			System.out.println("State the account that you would you like to make a deposit into");
			acc_ID = Integer.parseInt(scanner.nextLine());
			System.out.println("State the amount that you would like to deposit");
			amount = Integer.parseInt(scanner.nextLine());
			try {
				adminBank.deposit(acc_ID, amount);
				Customer customer= adminBank.getAccount(acc_ID);
				System.out.println(customer);
				logger.info("Succesfully deposited "+amount+" into account "+acc_ID);
			} catch (Exception e) {
				logger.warn("Failed to succesfully deposit "+amount+" into account "+acc_ID);
				e.printStackTrace();
			}
			break;
		case 4:
			System.out.println("State the account that you would you like to withdraw from");
			acc_ID = Integer.parseInt(scanner.nextLine());
			System.out.println("State the amount that you would like to withdraw");
			amount = Integer.parseInt(scanner.nextLine());
			try {
				adminBank.withdraw(acc_ID, amount);
				Customer customer= adminBank.getAccount(acc_ID);
				System.out.println(customer);
				logger.info("Succesfully withdrew "+amount+" from account "+acc_ID);
			} catch (Exception e) {
				logger.warn("Failed to succesfully withdraw "+amount+" from account "+acc_ID);
				e.printStackTrace();
			}
			break;
		case 5:
			System.out.println("Enter the ID of the account that you would like to approve");
			acc_ID = Integer.parseInt(scanner.nextLine());
			System.out.println("Enter '1' if you would like to approve the account");
			System.out.println("Enter '2' if you would like to decline the approval of the account");
			int decision = Integer.parseInt(scanner.nextLine());
			String status;
			switch (decision) {
			case 1:
				status = "Active";
				adminBank.updateStatus(acc_ID, status);
				System.out.println("Account approved");
				logger.info("Acccount "+acc_ID+" status set to "+status);
				break;
			case 2:
				status = "Declined";
				adminBank.updateStatus(acc_ID, status);
				System.out.println("Account not approved");
				logger.info("Acccount "+acc_ID+" denied approval ");
				break;
			default:
				System.out.println("Response not-valid");
				break;
			}
			break;
		case 6:
			System.out.println("Enter the ID of the account that you would like to view");
			acc_ID = Integer.parseInt(scanner.nextLine());
			adminBank.getUser(acc_ID);
			adminBank.getAccount(acc_ID);
			System.out.println(adminBank.getAccount(acc_ID));
			logger.info("Acccount "+acc_ID+" succesfully retrieved ");
			break;
		case 7:
			int recievingAccountID;
			int amountTransferred;
			System.out.println("State the account ID that the funds will be transferred from");
			int transferringAccountID = Integer.parseInt(scanner.nextLine());
			System.out.println("State the account ID that the funds will be transferred to");
			recievingAccountID = Integer.parseInt(scanner.nextLine());
			System.out.println("State the amount to be transferred");
			amountTransferred = Integer.parseInt(scanner.nextLine());
			try {
				adminBank.withdraw(transferringAccountID, amountTransferred);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Customer withdrawalUpdate2 = adminBank.getAccount(transferringAccountID);
			System.out.println(withdrawalUpdate2);
			
			try {
				adminBank.deposit(recievingAccountID, amountTransferred);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Customer depositUpdate2 = adminBank.getAccount(recievingAccountID);
			System.out.println(depositUpdate2);
			break;
		case 8:
			System.out.println("Thank you for visiting David's Banking Application");
			System.exit(0);
			break;
		default:
			break;
		}
	}else {
		System.out.println("");
		System.out.println("Incorrect Password");}
	}catch(Exception e) {
		System.out.println("Invalid input");
	}
}
}
