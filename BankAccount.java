package accMgtSys;

import java.io.IOException;
import java.io.Serializable;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.util.Date;


public class BankAccount extends Account implements Serializable{
	private double balance;
	private String name;
	private int PIN;
	public BankAccount(){
		name="";
		PIN=0000;
		balance=10000;
	}
	public BankAccount(double x){
		balance=x;
		name="";
		PIN=0000;
	}
	public double getBal(){
		return balance;
	}
	public void displayBalance(){
		System.out.println("You have $"+ft.format(balance)+" in your bank account");
	}
	public void deposit(double amt) throws IOException{
		Date dte=new Date();
		balance+=amt;
		makeBankHistory("Deposit "+amt+" "+balance+" "+dte.getHours()+":"+dte.getMinutes()+":"+dte.getSeconds());
	}
	public void withdraw(double amt) throws IOException{
		if(balance-amt>0){
			Date dte=new Date();
			balance-=amt;
			makeBankHistory("Withdrawl "+amt+" "+balance+" "+dte.getHours()+":"+dte.getMinutes()+":"+dte.getSeconds());
		}
		else
			System.out.println("Insufficient Funds");
	}
	public void displayTranshistory() throws IOException{
		displayBankHistory();
	}
	
	

}
