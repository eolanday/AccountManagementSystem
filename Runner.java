package accMgtSys;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
import java.util.concurrent.TimeUnit;
public class Runner extends FileMaster{
	public static void main(String[]args) throws InterruptedException, FileNotFoundException, ClassNotFoundException, IOException{
		print("Welcome to Olanday Bank");
		//TimeUnit.SECONDS.sleep(5);
		
		Scanner scn=new Scanner(System.in);
		int input;
		boolean tryagain=true;
		print("");
		while(tryagain){
			startMenu();
			input=scn.nextInt();
			switch(input){
			case 1:
				StockAccount user=readStcAcc();
				print("Stock Portfolio Account");
				int secIn;
				boolean tryagain2=true;
				while(tryagain2){
					portMenu();
					secIn=scn.nextInt();
					switch(secIn){
					case 1:
						String temp;
						print("Input Stock Symbol");
						scn.nextLine();
						temp=scn.nextLine();
						user.displayPrice(temp);
						print("");
						break;
					case 2:
						user.displayPortfolio();
						print("");
						break;
					case 3:
						String name;
						int shares;
						double max;
						print("Input Stock Symbol");
						scn.nextLine();
						name=scn.nextLine();
						print("Input Number of Shares");
						shares=scn.nextInt();
						print("Input Max Bid");
						max=scn.nextDouble();
						user.buyShares(name, shares, max);
						print("");
						break;
					case 4:
						String name2;
						int shares2;
						double min;
						print("Input Stock Symbol");
						scn.nextLine();
						name2=scn.nextLine();
						print("Input Number of Shares");
						shares2=scn.nextInt();
						print("Input Min Bid");
						min=scn.nextDouble();
						user.sellShares(name2, shares2, min);
						print("");
						break;
					case 5:
						user.displayTranshistory();
						print("");
						break;
					case 6:
						saveStcAcc(user);
						tryagain2=false;
						break;
					default:
						print("Invalid Input");
						tryagain2=true;
						break;
					}
				}
				
				
				break;
			case 2:
				BankAccount userB=readBnkAcc();
				print("Bank Account");
				int bnkIn;
				boolean tryagain3=true;
				while(tryagain3){
					bankMenu();
					bnkIn=scn.nextInt();
					switch(bnkIn){
					case 1:
						userB.displayBalance();
						print("");
						break;
					case 2:
						print("Input Amount to Deposit");
						double x=scn.nextDouble();
						userB.deposit(x);
						print("");
						break;
					case 3:
						print("Input Amount to Withdraw");
						double y=scn.nextDouble();
						userB.withdraw(y);
						print("");
						break;
					case 4:
						userB.displayTranshistory();
						print("");
						break;
					case 5:
						saveBnkAcc(userB);
						tryagain3=false;
						break;
					default:
						print("Invalid Input");
						tryagain3=true;
						break;
					}
				}
				break;
			case 3:
				tryagain=false;
				break;
			default:
				print("Invalid Input");
				tryagain=true;
				break;
			}
		}
		System.out.println("Thank You");
	}
	public static void print(String x){
		System.out.println(x);
	}
	public static void startMenu(){
		print("Please select an Account to Access:");
		print("\t1. Stock Portfolio Account");
		print("\t2. Bank Account");
		print("\t3. Exit");
		
	}
	public static void portMenu(){
		print("Please Select an Option: ");
		print("\t1. Display the Price for a Stock Symbol");
		print("\t2. Display the Current Portfolio");
		print("\t3. Buy Shares");
		print("\t4. Sell Shares");
		print("\t5. View Transaction History");
		print("\t6. Return to previous menu");
	}
	public static void bankMenu(){
		print("Please Select an Option: ");
		print("\t1. View Account Balance");
		print("\t2. Deposit Money");
		print("\t3. Withdraw Money");
		print("\t4. Display History");
		print("\t5. Return to Previous Menu");
	}
}
