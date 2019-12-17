package com.revature.Actions;

import java.util.Scanner;

import com.revature.bankingapplication.Bank;
import com.revature.bankingapplication.Customer;

public class UserOptions {
	
	public void userActions() {
		int accountID;
		int amount;
		Bank userBank = new Bank();
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("Enter your account ID");
		accountID = Integer.parseInt(scanner.nextLine());
		
		System.out.println("1. View account");
		System.out.println("2. Make a deposit");
		System.out.println("3. Make a withdrawal");
		System.out.println("4. Delete account");
		System.out.println("5. Exit application");
		
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
			System.out.println("Thank you for visiting David's Banking Application");
			System.exit(0);
			break;
		default:
			break;
			}
		}
	}
