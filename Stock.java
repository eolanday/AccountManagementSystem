package accMgtSys;

import java.io.Serializable;

public class Stock extends FileMaster implements Serializable{
	private String symbol;
	private double price;
	private int shares;
	public Stock(String x, double y){
		symbol=x;
		price=y;
		shares=0;
	}
	public Stock(String x, double y, int z){
		symbol=x;
		price=y;
		shares=z;
	}
	public String getSymbol(){
		return symbol;
	}
	public void setSymbol(String x){
		symbol=x;
	}
	public double getPrice(){
		return price;
	}
	public void setPrice(double x){
		price=x;
	}
	public int getShares(){
		return shares;
	}
	public void setShares(int x){
		shares=x;
	}
	public void addShares(int x){
		shares+=x;
	}
	public void removeShares(int x){
		shares-=x;
	}
	
}
