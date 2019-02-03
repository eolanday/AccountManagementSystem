package accMgtSys;

import java.io.IOException;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Date;

public class StockAccount extends Account implements Serializable{
	private ArrayList<Stock> portfolio;
	private String name;
	private int PIN;
	private double balance;
	public StockAccount(){
		name="";
		PIN=0000;
		portfolio=new ArrayList<Stock>();
		balance=10000;
	}
	public StockAccount(ArrayList<Stock>x, double y){
		name="";
		PIN=0000;
		portfolio=x;
		balance=y;
	}
	public ArrayList<Stock> getPort(){
		return portfolio;
	}
	public double getBal(){
		return balance;
	}
	public void displayPrice(String symbol) throws IOException{
		if(stockCheck(symbol))
			System.out.println("Company Symbol \t Stock Price \n "+symbol+" \t $"+getStockPrice(symbol));
		else
			System.out.println("Stock symbol not found");
	}
	public void displayPortfolio(){
		Stock temp;
		double total=0.0, portTotal=0.0;
		System.out.println("Cash Balance = $"+balance);
		System.out.println("Company Symbol \t Number \t Price per share \t Total Value");
		for(int i=0;i<portfolio.size();i++){
			temp=portfolio.get(i);
			total=(temp.getShares()*temp.getPrice());
			System.out.println(temp.getSymbol()+"\t"+temp.getShares()+"\t$"+ft.format(temp.getPrice())+"\t$"+ft.format(total));
			portTotal+=total;
		}
		System.out.println("Total Portfolio Value: $"+ft.format(portTotal));
	}
	public void buyShares(String ticker, int amount, double maxbid) throws IOException{
		if(stockCheck(ticker)){
			double price=getStockPrice(ticker);
			if(price<maxbid){
				if(price*amount>balance){
					System.out.println("Insufficient Funds");
				}else{
					int stock=searchPortStock(ticker);
					if(stock!=-1){
						Date dte=new Date();
						portfolio.get(stock).addShares(stock);
						balance-=(amount*price);
						makeHistory("Buy "+ticker+" "+amount+" "+price+" "+(price*amount)+" "+dte.getHours()+":"+dte.getMinutes()+":"+dte.getSeconds());
						System.out.println("Purchase Successful");
						System.out.println(amount+" shares of "+ticker+" at $"+ft.format(maxbid)+" each for a total of $"+ft.format(price*amount));
					}else{
						Date dte=new Date();
						portfolio.add(new Stock(ticker,price,amount));
						balance-=(amount*price);
						makeHistory("Buy "+ticker+" "+amount+" "+price+" "+(price*amount)+" "+dte.getHours()+":"+dte.getMinutes()+":"+dte.getSeconds());
						System.out.println("Purchase Successful");
						System.out.println(amount+" shares of "+ticker+" at $"+ft.format(maxbid)+" each for a total of $"+ft.format(price*amount));
					}
				}
			}else{
				System.out.println("Stock Bid Too Low");
			}
		}else{
			System.out.println("Stock Not Available");
		}
	}
	public void sellShares(String ticker, int amount, double minbid)throws IOException{
		if(stockCheck(ticker)){
			double price=getStockPrice(ticker);
			int stockLoc=searchPortStock(ticker);
			if(stockLoc>=0){
				if(amount<(portfolio.get(stockLoc).getShares())){
					if(price>minbid){
						Date dte=new Date();
						portfolio.get(stockLoc).removeShares(amount);
						balance+=amount*minbid;
						System.out.println("Transaction Successful");
						System.out.println(amount+" shares of "+ticker+" at $"+ft.format(minbid)+" each for a total of $"+ft.format(price*amount));
						makeHistory("Sell "+ticker+" "+amount+" "+price+" "+(price*amount)+" "+dte.getHours()+":"+dte.getMinutes()+":"+dte.getSeconds());
					}else{
						System.out.println("Sell Price is Higher than Price Per Stock");
					}
				}else{
					System.out.println("Insufficient Shares");
				}
			}else{
				System.out.println("Stock Not Available");
			}
		}else{
			System.out.println("Stock Not Available");
		}
	}
	public void displayTranshistory() throws IOException{
		displayPortHistory();
	}
	private int searchPortStock(String x){
		for(int i=0;i<portfolio.size();i++){
			if(portfolio.get(i).getSymbol().equals(x))
				return i;
		}
		return -1;
	}
}
