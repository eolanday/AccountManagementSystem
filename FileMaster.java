package accMgtSys;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Serializable;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

public class FileMaster{
	final static String FILE_PATH="L:\\.SCHOOL 2016\\AP Computer Science\\stocks";
	final static File RESULTS=new File(FILE_PATH+"\\results.txt");
	final static File HISTORY=new File (FILE_PATH+"\\history1.txt");
	final static File HISTORYBANK=new File(FILE_PATH+"\\historyBank1.txt");
	
/*
	static FileWriter output;
	static BufferedWriter outputs;
	
	static{
		try{
			output=new FileWriter(HISTORY, true);
			outputs=new BufferedWriter(outputs);
		}catch(final IOException e){
			throw new ExceptionInInitializerError(e.getMessage());
		}
	}

*/
	
/*
	public static void main(String[] args) throws FileNotFoundException, IOException{
		File user=new File(FILE_PATH+"\\bank1.txt");
		ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(user));
		output.writeObject(new BankAccount());
		output.close();
		System.out.println("Done");
		
	}
*/
	public static void makeBankHistory(String x) throws IOException{
		BufferedWriter outputs=new BufferedWriter(new FileWriter(HISTORYBANK, true));
		outputs.write(x);
		outputs.newLine();
		outputs.close();
	}
	public static void displayBankHistory() throws IOException{
		BufferedReader hist=new BufferedReader(new FileReader(HISTORYBANK));
		System.out.println("Event \tAmount \tBalance \tTime");
		String temp="";
		int space=0, start=0;
		while(temp!=null){
			space=0;
			start=0;
			temp=hist.readLine();
			if(temp!=null){
				for(int i=0;i<3;i++){
					space=temp.indexOf(" ", start);
					System.out.print(temp.substring(start, space)+"\t");
					if(start+(space+1)<temp.length())
					start=space+1;
				}
				System.out.print(temp.substring(space));
			}
			System.out.println();
			
		}
		hist.close();
	}
	public static BankAccount readBnkAcc() throws FileNotFoundException, IOException, ClassNotFoundException{
		File user=new File(FILE_PATH+"\\bank1.txt");
		ObjectInputStream input=new ObjectInputStream(new FileInputStream(user));
		BankAccount temp=(BankAccount)input.readObject();
		input.close();
		return temp;
	}
	public static void saveBnkAcc(BankAccount x) throws IOException{
		PrintWriter clear=new PrintWriter(FILE_PATH+"\\bank1.txt");
		clear.print("");
		clear.close();
		ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(FILE_PATH+"\\bank1.txt"));
		output.writeObject(new BankAccount(x.getBal()));
		output.close();
		System.out.println("All Changes Have Been Saved");
	}
	public static StockAccount readStcAcc()throws FileNotFoundException, IOException, ClassNotFoundException{
		File user=new File(FILE_PATH+"\\user1.txt");
		ObjectInputStream input=new ObjectInputStream(new FileInputStream(user));
		StockAccount temp=(StockAccount)input.readObject();
		input.close();
		return temp;
	}
	public static void saveStcAcc(StockAccount x) throws FileNotFoundException, IOException{
		PrintWriter clear=new PrintWriter(FILE_PATH+"\\user1.txt");
		clear.print("");
		clear.close();
		ObjectOutputStream output=new ObjectOutputStream(new FileOutputStream(FILE_PATH+"\\user1.txt"));
		output.writeObject(new StockAccount(x.getPort(),x.getBal()));
		output.close();
		System.out.println("All Changes Have Been Saved");
	}
	public void displayPortHistory() throws IOException{
		BufferedReader hist=new BufferedReader(new FileReader(HISTORY));
		System.out.println("Event \tCompany Symbol \tNumber \tPrice per Shares \tTotal Value \tTime");
		String temp="";
		int space=0, start=0;
		while(temp!=null){
			space=0;
			start=0;
			temp=hist.readLine();
			if(temp!=null){
				for(int i=0;i<5;i++){
					space=temp.indexOf(" ", start);
					System.out.print(temp.substring(start, space)+"\t");
					if(start+(space+1)<temp.length())
					start=space+1;
				}
				System.out.print(temp.substring(space));
			}
			System.out.println();
			
		}
		hist.close();
	}
	public void makeHistory(String x)throws IOException{
		BufferedWriter outputs=new BufferedWriter(new FileWriter(HISTORY, true));
		outputs.write(x);
		outputs.newLine();
		outputs.close();
	}
	public boolean stockCheck(String x)throws IOException{
		BufferedReader results=new BufferedReader(new FileReader(RESULTS));
		String str="";
		int space=0;
		while(str!=null){
			try {
				str=results.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			if(str!=null){
			space=str.indexOf(" ");
				if(str.substring(0, space).equals(x)){
					results.close();
					return true;
				}
			}
		}
		results.close();
		return false;
	}
	public double getStockPrice(String x)throws IOException{
		BufferedReader results=new BufferedReader(new FileReader(RESULTS));
		String str="";
		int space=0;
		while(str!=null){
			try {
				str=results.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			space=str.indexOf(" ");
			if(str.substring(0, space).equals(x)){
				results.close();
				return Double.parseDouble(str.substring(space));
			}
		}
		results.close();
		return -1;
	}
}
