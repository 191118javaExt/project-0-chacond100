package com.revature;

import java.util.Scanner;

import org.apache.log4j.Logger;

import com.revature.Actions.*;

public class Menu {
	final static Logger logger = Logger.getLogger(Menu.class);
		public void menuSelection() {
			int choice = -1;
			while(true) {	
				System.out.println("");
				System.out.println("Welcome to David's banking app");
				System.out.println("Please select one of the options below");
				System.out.println("1. Create new Account");
				System.out.println("2. View/Edit existing account");
				System.out.println("3. Employee");
				System.out.println("4. Administration");
				System.out.println("5. Exit application");
		
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		
		try {
			choice = Integer.parseInt(scanner.nextLine());
			switch (choice) {
			case 1:
				CustomerOptions customer = new CustomerOptions();
				customer.customerActions();
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
			}catch(Exception a) {
				System.out.println("Invalid input");
				logger.warn("Invalid input during menu selection");
			}
		}
	}		
}