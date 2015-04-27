package com.mta.javacourse.model;

import com.mta.javacourse.Stock;

public class Portfolio {
	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portSize;
	
	public Portfolio (){
		this.title= new String (" ");
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portSize=0;
	}
	public void addStock(Stock stock){
		if(portSize<MAX_PORTFOLIO_SIZE && stock != null){
			stocks[this.portSize] = stock;
			portSize++;
		}
	
		else {
			System.out.println("Sorry, Portfolio is Full OR Stock is NULL");
		}

	}
public String getHtmlString(){
		
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.getTitle()+"</h1> <br>";
		
		for(int i=0; i<portSize;i++)
		{
			Stock tStock = stocks[i];
			htmlResString = htmlResString + tStock.getHtmlDescription()+"<br>";
		}
		
		return htmlResString;	
	}
	
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Stock[] getStock() {
		return stocks;
	}
	public void setStock(Stock[] stock) {
		this.stocks = stocks;
	}
	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}
	
}
