package app;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) 
	{
		example1();
	}
	
	public static void example1()
	{
		
		Scanner S = new Scanner(System.in);
		Bank bank = new Bank("Bank of CPHS");
		bank.loadAccounts("Accounts.txt");
		int UI = 0;
		
		
		while(UI < 100) {
		System.out.println("Welcome to The CPHS bank");
		System.out.println("________________________");
		System.out.println("1. Deposit");
		System.out.println("2. Withdraw");
		System.out.println("3. Check Balance");
		System.out.println("4. Open an account");
		System.out.println("5. Close an account");
		System.out.println("6. Transfer");
		System.out.println("7. Quit menu ");
		System.out.println(" ");
		UI = S.nextInt();
	    S.nextLine();
	    int Nu,Am;

	    switch(UI){


	    case 1 : System.out.println("Enter you account's PIN.");
	    Nu=S.nextInt();
	    System.out.println("How much money would you like to deposit?");
	    Am=S.nextInt();
	    bank.deposit(Nu, Am);
	    break;
	    
	    
	    case 2 : System.out.println("Enter you account's PIN.");
	    Nu=S.nextInt();
	    System.out.println("How much money woud you like to withdraw?");
	    Am=S.nextInt();
	    bank.withdraw(Nu, Am);
	    break;
	    
	    
	    case 3 : System.out.println("Enter your PIN.");
	    Nu=S.nextInt();
	    bank.checkBalance(Nu);
	    break;
	    
	    
	    case 4 : System.out.println("Whats the name of the new account?");
	    String ACName=S.nextLine();
	    Nu = bank.createAccount(ACName);
	    break;
	    
	    
	    case 5 : System.out.println("Enter the PIN of the account");
	    int ACCD=S.nextInt();
		bank.closeAccount(ACCD);
	    break;
	    
	    
	    case 6 : System.out.println("What account (PIN) do you whant to get the money from?");
	    int Nu1=S.nextInt();
	    System.out.println("What account (PIN) do you whant to give the money to?");
	    int Nu2=S.nextInt();
	    System.out.println("How much money do you whant to transefer?");
	    Am=S.nextInt();
	    
	    bank.withdraw(Nu1, Am);
	    bank.deposit(Nu2, Am);
	    
	    System.out.println("Transfer completed");

	    break;
	    
	    case 7: System.exit(1);
	    }
	    bank.saveAccounts("Accounts.txt");
	    }
	}
}