package com.revature.Actions;

import java.util.InputMismatchException;
import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.bankingapplication.Bank;
import com.revature.bankingapplication.Customer;

public class UserOptions {
	
	final static Logger logger = Logger.getLogger(UserOptions.class);
	
	int accountID;
	int amount;
	Bank userBank = new Bank();
	Scanner scanner = new Scanner(System.in);	
	
	public void userActions() {
		
		try {
			
			System.out.println("Enter your account ID");
			accountID = Integer.parseInt(scanner.nextLine());
			String usernamePassword = userBank.login(accountID);
			System.out.println("Please enter your username");
			String usernameInput = scanner.nextLine();
			System.out.println("Please enter you password");
			String passwordInput = scanner.nextLine();
			String loginAttempt = usernameInput+passwordInput;
		
			if(loginAttempt.equals(usernamePassword)) {
			logger.info("Succesful user account verification");
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
				logger.info("Account "+accountID+" retrieved succesfully");
				System.out.println(customer);
				break;
			case 2:
				System.out.println("Insert amount to deposit");
				amount = Integer.parseInt(scanner.nextLine());
				try {
					userBank.deposit(accountID, amount);
					logger.info("Succesfully deposited "+amount+" into account "+accountID);
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("Failed to deposit"+amount+" into account "+accountID);
				}
				Customer depositUpdate = userBank.getAccount(accountID);
				System.out.println(depositUpdate);
				break;
			case 3:
				System.out.println("Insert amount to withdraw");
				amount = Integer.parseInt(scanner.nextLine());
				try {
					userBank.withdraw(accountID, amount);
					logger.info("Succesfully withdrew "+amount+" from account "+accountID);
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("Failed to withdraw "+amount+" from account "+accountID);
				}
				Customer withdrawUpdate = userBank.getAccount(accountID);
				System.out.println(withdrawUpdate);
				break;
			case 4:
				userBank.deleteAccount(accountID);
				logger.info("Succesfully deleted account "+accountID);
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
					logger.info("Succesfully withdrew "+amount+" from account "+accountID);
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("Failed to withdraw "+amount+" from account "+accountID);
				}
				Customer withdrawalUpdate2 = userBank.getAccount(accountID);
				System.out.println(withdrawalUpdate2);
				
				try {
					userBank.deposit(recievingAccountID, amount);
					logger.info("Succesfully deposited "+amount+" into account "+accountID);
				} catch (Exception e) {
					e.printStackTrace();
					logger.warn("Failed to deposit "+amount+" into account "+accountID);
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
				logger.warn("Failed user account verification");
				System.out.println("Log in attempt failed");
				System.out.println("");
			}
		}catch(InputMismatchException a) {
		logger.warn("Invalid input in user menu");
		System.out.println("Invalid Input");
		}
	}
}
