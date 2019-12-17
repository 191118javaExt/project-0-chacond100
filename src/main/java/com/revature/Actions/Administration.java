package com.revature.Actions;

import java.util.ArrayList;
import java.util.Scanner;

import com.revature.bankingapplication.Bank;
import com.revature.bankingapplication.Customer;

public class Administration {
	
	public void administrativeActions(){
		Bank adminBank = new Bank();
		
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		System.out.println("1. View all accounts");
		System.out.println("2. Delete single account");
		System.out.println("3. Make deposit into account");
		System.out.println("4. Make withdrawal from account");
		System.out.println("5. Approve account");
		System.out.println("6. Exit application");
		
		int choice = Integer.parseInt(scanner.nextLine());
		int acc_ID;
		int amount;
		
		switch (choice) {
		case 1:
			ArrayList<Customer> allAccounts = adminBank.getAllAccounts();
			System.out.println(allAccounts);
			break;
		case 2:
			System.out.println("Enter the ID of the account that you would like to delete");
			acc_ID = Integer.parseInt(scanner.nextLine());
			adminBank.deleteAccount(acc_ID);
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
			} catch (Exception e) {
				// TODO Auto-generated catch block
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
				break;
			case 2:
				status = "Declined";
				adminBank.updateStatus(acc_ID, status);
				System.out.println("Account not approved");
				break;
			default:
				System.out.println("Response not-valid");
				break;
			}
			break;
		case 6:
			System.out.println("Thank you for visiting David's Banking Application");
			System.exit(0);
			break;
		default:
			break;
		}
	}
}
