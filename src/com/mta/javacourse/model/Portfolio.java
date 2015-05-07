package com.mta.javacourse.model;

import com.mta.javacourse.Stock;


/**
 * Portfolio contains info about stocks
 * @author OmriAlfassi
 */

public class Portfolio {
	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portSize;

	public Portfolio (String title){
		this.title= new String (title);
		stocks = new Stock[MAX_PORTFOLIO_SIZE];
		this.portSize=0;
	}
	
	/**
	 * copy C'tor for Portfolio type.
	 * @param originalPortfolio
	 * @author OmriAlfassi
	 */
	public Portfolio (Portfolio originalPortfolio){
		
		this(originalPortfolio.getTitle());
		this.portSize = originalPortfolio.getPortfolioSize();
		
		copyStocksArray(originalPortfolio.getStock(), this.getStock());	
	}
	
	/**
	 * takes an original stock array and copy it to a new one.
	 * @param originalStocksArray
	 * @param newStocksArray
	 * @author OmriAlfassi
	 */
	
	private void copyStocksArray(Stock[] originalStocksArray, Stock[] newStocksArray ){
			
		for(int i = 0; i<this.portSize; i++){
			newStocksArray[i]= new Stock (originalStocksArray[i]);
		
		}
	}
	/**
	 * addStock to stocks array.
	 * @param stock
	 * @author OmriAlfassi
	 */
	public void addStock(Stock stock){
		if(portSize<MAX_PORTFOLIO_SIZE && stock != null){
			stocks[this.portSize] = stock;
			portSize++;
		}

		else {
			System.out.println("Sorry, Portfolio is Full OR Stock is NULL");
		}	
	}
	
	/**
	 * delete stocks with same symbol as asked to delete 
	 * @param stock
	 */
	public void deleteStock(Stock stock){
		for(int i = 0; i< this.portSize; i++){
			if((this.stocks[i].getSymbol().equals(stock.getSymbol()))==true){
				stocks[i] = stocks[this.portSize-1];
				stocks[this.portSize-1]= null;
				this.portSize--;
				System.out.println("Stock deleted");
				return;
			}
		}
		System.out.println("Stock is not in Portfolio");
		return;
	}
	/**
	 * print string.
	 * @author OmriAlfassi
	 */
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
	public  int getPortfolioSize() {
		return portSize;
	}
	public static int getMaxPortfolioSize() {
		return MAX_PORTFOLIO_SIZE;
	}
}
