package app;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import app.Bank.Account;

public class Bank {
	// Variable for logging/not logging
	private static final boolean LOG = true;

	private static int accountCounter = 1;
	private String name;
	private ArrayList<Account> accounts;

	public Bank() {
		this("Bank Name");
	}

	public Bank(String name) {
		this.name = name;
		accounts = new ArrayList<>();
		log("Bank Created");
	}

	public int createAccount(String name) {
		Account newAccount = new Account(name);
		accounts.add(newAccount);

		log("Added account " + newAccount);
		return newAccount.accountNumber;
	}

	public boolean closeAccount(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not close account " + accountNumber);
			return false;
		}
		accounts.remove(account);
		log("Successfully closed " + account);
		return true;
	}

	public boolean deposit(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not deposit to account " + accountNumber);
			return false;
		}
		account.balance += amount;
		log("Successfully deposited $" + amount + " to " + account);
		return true;
	}

	public boolean withdraw(int accountNumber, int amount) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not withdraw from account " + accountNumber);
			return false;
		}
		if (account.balance < amount) {
			log("Insufficient funds in " + account);
			return false;
		}
		account.balance -= amount;
		log("Successfully withdrew $" + amount + " from " + account);
		return true;
	}

	public int checkBalance(int accountNumber) {
		Account account = findAccount(accountNumber);
		if (account == null) {
			log("Could not check balance of account " + accountNumber);
			return -1;
		}
		log("Successfully checked balance of account " + account);
		return account.balance;
	}

	public void saveAccounts(String filename) { ///////////////////////////////////////////////////////////////////////////////////////////////
		// TODO
		try {
			FileWriter fw = new FileWriter(filename);
			
			for(Account a: accounts) //enhanced for loop / for each loop
			{
				
				String message = a.toString() + "\n";
				fw.append(message);
			}
			
			
			
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log("Accounts saved");
	}

	public void loadAccounts(String filename) {  ///////////////////////////////////////////////////////////////////////////////////////////////////////
		// Go through eachline
		//Turn line into account
		// Put account in arraylist
		try {
			Scanner fileScanner = new Scanner(new File(filename));
			
			while(fileScanner.hasNextLine())
			{
				String line = fileScanner.nextLine();
				String[] split = line.split("::");
				int acctNo = Integer.parseInt(split[0].substring(1));
				String name = split [1];
				int amt = Integer.parseInt(split[2].substring(1, split[2].length()-1));
				Account a= new Account(acctNo, name, amt);
				accounts.add(a);
			}
			
			fileScanner.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		log("Loaded file " + filename);
		}

	private Account findAccount(int accountNumber) {
		for (int i = accounts.size() - 1; i >= 0; i--) {
			if (accounts.get(i).accountNumber == accountNumber)
				return accounts.get(i);
		}
		return null;
	}

	private void log(String message) {
		if (LOG)
			System.out.println(name + " ::: " + message + ".");
	}

	/**
	 * Private Inner Class Account
	 * Deals with Account information
	 */
	public class Account {
		int accountNumber;
		String name;
		int balance;

		private Account(String name) {
			this.name = name;
			balance = 0;
			accountNumber = getRandomAccountNumber();
		}
		
		private Account(int an, String name, int bal){
			this.accountNumber = an;
			this.name = name;
			this.balance = bal;
			}
		
		private int getRandomAccountNumber() {
			boolean flag = true;
			int num = 0;
			do {
				num = (int)(Math.random()*1000000);
				for( Account a:accounts) {
					if(a.accountNumber == num)
						flag = true;
				}
				flag = false;
				
			}while(flag);
			return num;
		}

		public String toString() {
			return "{" + accountNumber + "::" + name + "::$" + balance + "}";
		}

	}
}

