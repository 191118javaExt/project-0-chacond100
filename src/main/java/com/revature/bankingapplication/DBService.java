package com.revature.bankingapplication;

import org.apache.log4j.Logger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBService{
//this is where we are going to interact with our database
	private static Logger logger = Logger.getLogger(DBService.class);
	
	String url = "jdbc:postgresql://localhost:5432/";
	String username = "postgres";
	String password = "Saleen@2501";
	
	//Connection
	public Connection connect() {	
		Connection connection;
		try {
			connection = DriverManager.getConnection(url,username,password);
		} catch (SQLException e) {
			logger.warn("Unable to obtain connection to database", e);
			connection = null;
		}
		return connection;
	}
	
	//Create
	int addAccount(String firstName, String lastName, String username, String password, AccountType accountType, Double balance, String status) {
		int userID=-1;
		int accountID=-1;
		//Connection connection = connect();
		try (Connection connection = connect()){
		connection.setAutoCommit(false);
		//Add User
		String addUserSql = "INSERT INTO Users (firstName,lastName,username,password) VALUES (?,?,?,?)";
		PreparedStatement addUser = connection.prepareStatement(addUserSql, Statement.RETURN_GENERATED_KEYS);
		addUser.setString(1, firstName);
		addUser.setString(2, lastName);
		addUser.setString(3, username);
		addUser.setString(4, password);
		addUser.executeUpdate();
		ResultSet addUserResults = addUser.getGeneratedKeys();
		if(addUserResults.next()) {
			userID = addUserResults.getInt(1);
		}
		//Add Account
		String addAccountSql = "INSERT INTO Accounts (Type, Balance, Status) VALUES (?,?, ?)";
		PreparedStatement addAccount = connection.prepareStatement(addAccountSql, Statement.RETURN_GENERATED_KEYS);
		addAccount.setString(1, accountType.name());
		addAccount.setDouble(2, balance);
		addAccount.setString(3, status);
		addAccount.executeUpdate();
		ResultSet addAccountResults = addAccount.getGeneratedKeys();
		if(addAccountResults.next()) {
			accountID=addAccountResults.getInt(1);
		}
		//Link User to Account
		if(userID>0 && accountID>0) {
			String linkAccountSql="INSERT INTO Mappings(UserID, AccountID) VALUES (?,?)";
			PreparedStatement linkAccount = connection.prepareStatement(linkAccountSql);
			linkAccount.setInt(1, userID);
			linkAccount.setInt(2, accountID);
			linkAccount.executeUpdate();
			connection.commit();
		}else {
			connection.rollback();
		}
		//connection.close();
		addUserResults.close();
		addAccountResults.close();
		}catch(SQLException ex){
			logger.warn("Unable to add account", ex);
		}
		return accountID;
	}
	
	//Read
	Customer getAccount(int accountID) {
		Customer customer = null;
		//Connection connection = connect();
		try(Connection connection = connect()){
		String findUserSql = "SELECT * FROM Accounts WHERE Accounts.ID=?";
		PreparedStatement findUser = connection.prepareStatement(findUserSql);
		findUser.setInt(1, accountID);
		ResultSet findUserResults = findUser.executeQuery();
		if(findUserResults.next()) {
		AccountType accountType=AccountType.valueOf(findUserResults.getString("Type")); 
		Double balance=findUserResults.getDouble("Balance");
		String status = findUserResults.getString("Status");
			Account account;
			if(accountType == AccountType.Checking) {
				account = new Checking(accountID, balance, status);
		}else if (accountType == AccountType.Savings){
				account = new Savings(accountID, balance, status);
		}else {
			throw new Exception("Unknown account type");
			}
			customer = new Customer(account);
		}
		else {
			logger.warn("No user account was found for ID"+ accountID);
			}
		findUserResults.close();
		}catch(Exception e) {
			logger.warn("Unable to retrieve account", e);
		}
		return customer;
	}
	
	String getUser(int accountID) {
	String userInformation = null;
	try(Connection connection = connect()){
	String findUserSql = "SELECT * FROM Users WHERE Users.ID=?";
	PreparedStatement findUser = connection.prepareStatement(findUserSql);
	findUser.setInt(1, accountID);
	ResultSet findUserResults = findUser.executeQuery();
	if(findUserResults.next()) { 
	String firstName = findUserResults.getString("firstName");
	String lastName = findUserResults.getString("lastName");
	String username = findUserResults.getString("username");
	String password = findUserResults.getString("password");
	
	userInformation = "First name: " + firstName+ " Last name: " +lastName+ " Username: "+username+" Password: "+password;
		}
	} catch (SQLException e) {
		logger.warn("Unable to retrieve User", e);
		e.printStackTrace();
	} 
	return userInformation;
	}

	//UpdateBalance
	boolean updateAccount(int accountID, Double balance) {
		boolean success = false;
		//Connection connection = connect();
		try (Connection connection = connect()){
		String updateSql = "UPDATE Accounts SET Balance = ? WHERE ID=?";
		PreparedStatement updateBalance = connection.prepareStatement(updateSql);
		updateBalance.setDouble(1, balance);
		updateBalance.setInt(2, accountID);
		updateBalance.executeUpdate();
		success = true;
		}catch (SQLException ex) {
			logger.warn("Unable to update account", ex);
		}
		return success;
	}
	
	//UpdateStatus
	boolean updateStatus(int accountID, String status) {
		boolean success = false;
		//Connection connection = connect();
		try (Connection connection = connect()){
		String updateSql = "UPDATE Accounts SET Status = ? WHERE ID=?";
		PreparedStatement updateStatus = connection.prepareStatement(updateSql);
		updateStatus.setString(1, status);
		updateStatus.setInt(2, accountID);
		updateStatus.executeUpdate();
		success = true;
		}catch (SQLException ex) {
			logger.warn("Unable to update account", ex);
		}
		return success;
	}
	
	//Delete
	boolean deleteAccount(int accountID) {
		boolean success = false;
		//Connection connection = connect();
		try (Connection connection = connect()){
		String deleteSql = "DELETE FROM Accounts WHERE Accounts.ID=?";
		PreparedStatement deleteAccount = connection.prepareStatement(deleteSql);
		deleteAccount.setInt(1, accountID);
		deleteAccount.executeUpdate();
		success = true;
		}catch (SQLException ex) {
			logger.warn("Unable to delete account", ex);
		}
		return success;
	}

	//Get All Accounts
	ArrayList<Customer> getAllAccounts(){
		ArrayList<Customer> customers = new ArrayList<>();
		Connection connection = connect();
		try {
		String findAllUsersSql = "SELECT * FROM Accounts";
		PreparedStatement findAllUsers = connection.prepareStatement(findAllUsersSql);
		ResultSet findUserResults = findAllUsers.executeQuery();
		while(findUserResults.next()) {
			AccountType accountType=AccountType.valueOf(findUserResults.getString("Type")); 
			Double balance = findUserResults.getDouble("Balance");
			int accountID = findUserResults.getInt("ID");
			String status = findUserResults.getString("Status");
			Account account;
			if(accountType == AccountType.Checking) {
				account = new Checking(accountID, balance, status);
			}else if (accountType == AccountType.Savings){
				account = new Savings(accountID, balance, status);
			}else {
				throw new Exception("Unknown account type");
			}
			customers.add(new Customer(account));
		}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return customers;
	}
	
	//Login
	String login(int accountID) {
		
		String together = null;
		try(Connection connection = connect()){
		String findUserSql = "SELECT username, password FROM Users WHERE User.ID=?";
		PreparedStatement findUser = connection.prepareStatement(findUserSql);
		findUser.setInt(1, accountID);
		ResultSet findUserResults = findUser.executeQuery();
		if(findUserResults.next()) { 
		String username = findUserResults.getString("username");
		String password = findUserResults.getString("password");
		together = username+password;
			}
		} catch (SQLException e) {
			//logger.warn("Unable to succesfully log in");
		} 
		return together;
	}
	
}



