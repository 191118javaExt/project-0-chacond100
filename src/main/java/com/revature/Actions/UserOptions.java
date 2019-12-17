package com.revature.Actions;

import java.util.Scanner;

import com.revature.bankingapplication.Bank;
import com.revature.bankingapplication.Customer;

public class UserOptions {
	int accountID;
	int amount;
	Bank userBank = new Bank();
	Scanner scanner = new Scanner(System.in);	
	
	public void userActions() {
		
		
		System.out.println("Enter your account ID");
		accountID = Integer.parseInt(scanner.nextLine());
		
		String usernamePassword = userBank.login(accountID);
		System.out.println("Please enter your username");
		String usernameInput = scanner.nextLine();
		System.out.println("Please enter you password");
		String passwordInput = scanner.nextLine();
		String loginAttempt = usernameInput+passwordInput;
	
		if(loginAttempt.equals(usernamePassword)) {
		
		System.out.println("Log in attempt successful");
		System.out.println("");
		
		System.out.println("1. View account");
		System.out.println("2. Make a deposit");
		System.out.println("3. Make a withdrawal");
		System.out.println("4. Delete account");
		System.out.println("5. Transfer funds");
		System.out.println("6. Exit application");
		
		int choice = Integer.parseInt(scanner.nextLine());
		
		
		switch(choice) {
		case 1:
			Customer customer= userBank.getAccount(accountID);
			System.out.println(customer);
			break;
		case 2:
			System.out.println("Insert amount to deposit");
			amount = Integer.parseInt(scanner.nextLine());
			try {
				userBank.deposit(accountID, amount);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Customer depositUpdate = userBank.getAccount(accountID);
			System.out.println(depositUpdate);
			break;
		case 3:
			System.out.println("Insert amount to withdraw");
			amount = Integer.parseInt(scanner.nextLine());
			try {
				userBank.withdraw(accountID, amount);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Customer withdrawUpdate = userBank.getAccount(accountID);
			System.out.println(withdrawUpdate);
			break;
		case 4:
			userBank.deleteAccount(accountID);
			break;
		case 5:
			int recievingAccountID;
			int amount;
			
			System.out.println("State the account ID that the funds will be transferred to");
			recievingAccountID = Integer.parseInt(scanner.nextLine());
			System.out.println("State the amount to be transferred");
			amount = Integer.parseInt(scanner.nextLine());
			try {
				userBank.withdraw(accountID, amount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Customer withdrawalUpdate2 = userBank.getAccount(accountID);
			System.out.println(withdrawalUpdate2);
			
			try {
				userBank.deposit(recievingAccountID, amount);
			} catch (Exception e) {
				e.printStackTrace();
			}
			Customer depositUpdate2 = userBank.getAccount(recievingAccountID);
			System.out.println(depositUpdate2);
			break;
		case 6:
			System.out.println("Thank you for visiting David's Banking Application");
			System.exit(0);
			break;
		default:
			break;
			}
		}else {
			System.out.println("Log in attempt failed");
			System.out.println("");
		}
	}
}
