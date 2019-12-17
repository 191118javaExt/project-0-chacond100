package com.revature;

import java.util.Scanner;

import com.revature.Actions.*;

public class Menu {
	
		public void menuSelection() {
	while(true) {	
		System.out.println("               ");
		System.out.println("Welcome to David's banking app");
		System.out.println("Please select one of the options below");
		System.out.println("1. Create new Account");
		System.out.println("2. View/Edit existing account");
		System.out.println("3. Employee");
		System.out.println("4. Administration");
		System.out.println("5. Exit application");
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		int choice = Integer.parseInt(scanner.nextLine());
		
		switch (choice) {
		case 1:
			CustomerOptions customer = new CustomerOptions();
			customer.newCustomerActions();
			break;
		case 2:
			UserOptions user = new UserOptions();
			user.userActions();
			break;
		case 3:
			EmployeeOptions employee = new EmployeeOptions();
			employee.employeeActions();
			break;
		case 4:
			Administration administration = new Administration();
			administration.administrativeActions();
			break;
		case 5:
			System.out.println("Thanks for visiting David's Banking Application");
			System.exit(0);
		default:
			break;
			}
		}
	}
}