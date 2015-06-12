package com.mta.javacourse.model;

import java.text.DecimalFormat;

import org.algo.model.PortfolioInterface;
import org.algo.model.StockInterface;






import com.mta.javacourse.excepsion.BalanceException;
import com.mta.javacourse.excepsion.PortfolioFullException;
import com.mta.javacourse.excepsion.StockAlreadyExistsException;
import com.mta.javacourse.excepsion.StockNotExistException;
import com.sun.org.apache.bcel.internal.generic.RETURN;

import org.algo.exception.PortfolioException;
/**
 * Portfolio contains info about stocks
 * @author OmriAlfassi
 */

public class Portfolio implements PortfolioInterface {
	private final static int MAX_PORTFOLIO_SIZE = 5;

	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD 
	}

	private String title;
	private StockInterface[] stocks;
	private int portSize;
	private float balance;

	/**
	 * C'tor of Portfolio .
	 * @param 
	 * @author OmriAlfassi
	 */
	public Portfolio() {
		this.title = new String("");
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.portSize = 0;
		this.balance = 0;

	}
	/**
	 * C'tor of Portfolio .
	 * @param title
	 * set title by parameter
	 * @author OmriAlfassi
	 */
	public Portfolio (String title){
		this.title= new String (title);
		stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.portSize=0;
		this.balance = 0;
	}
	/**
	 * C'tor of Portfolio .
	 * @param stock array
	 * creates new instance using a stock array
	 * @author OmriAlfassi
	 */
	public Portfolio(StockInterface[] stocksArray) {
		this.title = new String("Temporary Title");
		this.stocks = new StockInterface[MAX_PORTFOLIO_SIZE];
		this.portSize = stocksArray.length;
		this.copyStocksArray(stocksArray, stocks);
		this.balance = 0;
	}

	/**
	 * copy C'tor for Portfolio type.
	 * @param originalPortfolio
	 * @author OmriAlfassi
	 */
	public Portfolio (Portfolio originalPortfolio){

		this(originalPortfolio.getTitle());
		this.portSize = originalPortfolio.getPortfolioSize();

		copyStocksArray(originalPortfolio.getStocks(), this.getStocks());	
	}

	/**
	 * takes an original stock array and copy it to a new one.
	 * @param originalStockInterface
	 * @param newStocksArray
	 * @author OmriAlfassi
	 */

	private void copyStocksArray(StockInterface[] originalStockInterface, StockInterface[] newStockInterfaces ){

		for(int i = 0; i<this.portSize; i++){
			newStockInterfaces[i]= new Stock ((Stock)originalStockInterface[i]);

		}
	}
	/**
	 * addStock to stocks array.
	 * @param stock
	 * @author OmriAlfassi
	 */
	public void addStock(Stock stock) throws StockAlreadyExistsException, PortfolioFullException{
		if(this.portSize<MAX_PORTFOLIO_SIZE && stock != null){
			for (int i=0;i<this.portSize;i++){
				if (this.stocks[i].getSymbol().equals(stock.getSymbol())){
					throw new StockAlreadyExistsException(stock.getSymbol());
				}
			}
			stocks[this.portSize] = (StockInterface) stock;//
			((Stock) stocks[this.portSize]).setStockQuantity(0);
			portSize++;
			return;
		}
		else {
			if (stock == null){
				System.out.println("Stock is Invalide");
			}
			else{
				throw new PortfolioFullException();
			}	
		}
	}
	/**
	 * check if stock is already in portfolio.
	 * if true returns index if false return -1
	 * @return i/-1
	 * @param symbol
	 */
	public int isStockInPort (String symbol){
		for (int i=0;i<this.portSize;i++){
			if (this.stocks[i].getSymbol().equals(symbol)){
				return i;
			}
		}
		return -1;

	}

	/**
	 * delete stocks with same symbol as asked to delete 
	 * @param stock
	 */
	public void deleteStock(StockInterface stock) throws StockNotExistException{
		for(int i = 0; i< this.portSize; i++){
			if((this.stocks[i].getSymbol().equals(stock.getSymbol()))){
				stocks[i] = stocks[this.portSize-1];
				stocks[this.portSize-1]= null;
				this.portSize--;
				System.out.println("Stock deleted");
				return;
			}
		}
		throw new StockNotExistException("Cant delete, The stock name is invalid!");
	}

	/**
	 * remove stock from portfolio by selling the stock and deleting it fom portfolio
	 * @return boolean
	 * @param symbol
	 */
	public void removeStock(String symbol) throws StockNotExistException, BalanceException{
		if (symbol == null){
			throw new StockNotExistException("The stock name is invalid!");
		}
		if (isStockInPort(symbol)>-1){
			int i=isStockInPort(symbol);
			try{
				sellStock(symbol, -1);
			}catch (IllegalArgumentException e) {
				e.getMessage();
				e.printStackTrace();
				throw e;
			} catch (StockNotExistException e) {
				e.getMessage();
				e.printStackTrace();
				throw e;
			}
			try{
				deleteStock(this.stocks[i]);
			}catch 	(StockNotExistException e) {
				e.getMessage();
				e.printStackTrace();
				throw e;
			}
		}
		else{
			throw new StockNotExistException("You dont have this stock in portfolio");
		}
	}
	/**
	 * sell specific stock by amount 
	 * @return boolean
	 * @param symbol, quantity
	 */
	public void sellStock (String symbol,int quantity) throws IllegalArgumentException, StockNotExistException,BalanceException{
		if (quantity<-1){
			throw new IllegalArgumentException("The number of stocks you wish to sell is invalid!");
		}
		else{
			if (isStockInPort(symbol)==-1){
				throw new StockNotExistException("stock"+symbol+"does not exists");
			}
			else{
				int i=isStockInPort(symbol);
				if (((Stock) this.stocks[i]).getStockQuantity()<quantity){
					throw new IllegalArgumentException("Stocks quantity is too big");
				}
				else{
					if (quantity!= -1){
						updateBalance(quantity*this.stocks[i].getBid());
						int amount=((Stock) this.stocks[i]).getStockQuantity()-quantity;
						((Stock) this.stocks[i]).setStockQuantity(amount);
						((Stock) this.stocks[i]).setRecommendation(ALGO_RECOMMENDATION.SELL);
					}
					else{
						updateBalance(((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid());
						((Stock) this.stocks[i]).setStockQuantity(0);
						((Stock) this.stocks[i]).setRecommendation(ALGO_RECOMMENDATION.SELL);
					}
				}
			}

		}
	}
	/**
	 * buyStock add a new stock if necessary and add the quantity to stock and deduct from balance the quantity*ask
	 * @return boolean
	 * @param stock, quantity
	 */
	public void buyStock (Stock stock, int quantity)throws IllegalArgumentException, PortfolioFullException, BalanceException, StockAlreadyExistsException, StockNotExistException{
		if (stock == null){
			throw new IllegalArgumentException("Invalid stock");
		}
		else{
			if (stock.getAsk()*quantity>this.balance || this.balance<stock.getAsk()){
				throw new BalanceException();
			}
			else{
				if (quantity<-1){
					throw new IllegalArgumentException("Invalid quantity amount");
				}
				else{
					if (isStockInPort(stock.getSymbol())==-1){
						if (this.portSize==MAX_PORTFOLIO_SIZE){
							throw new PortfolioFullException();
						}
						else{
							try {
								addStock(stock);
							} catch(StockAlreadyExistsException e) {
								e.getMessage();
								e.printStackTrace();
								throw e;
							} catch (PortfolioFullException e){
								e.getMessage();
								e.printStackTrace();
								throw e;
							}
							int i=isStockInPort(stock.getSymbol());
							if (quantity == -1){
								int amount = (int) (this.balance/this.stocks[i].getAsk());	
								((Stock) this.stocks[i]).setStockQuantity(amount);
								this.balance -=amount*this.stocks[i].getAsk();
							}
							else{
								((Stock) this.stocks[i]).setStockQuantity(quantity);
								this.balance -=quantity*this.stocks[i].getAsk();
							}
						}
					}


					else{
						int i=isStockInPort(stock.getSymbol());
						if (quantity == -1){
							int amount = (int) (this.balance/this.stocks[i].getAsk());
							this.balance -=amount*this.stocks[i].getAsk();
							amount +=((Stock) this.stocks[i]).getStockQuantity();
							((Stock) this.stocks[i]).setStockQuantity(amount);
						}
						else{
							this.balance -=this.stocks[i].getAsk()*quantity;
							int amount=((Stock) this.stocks[i]).getStockQuantity()+quantity;
							((Stock) this.stocks[i]).setStockQuantity(amount);
						}
					}
				}
			}
		}
	}	

	/**
	 * print string.
	 * @return htmlResString
	 * @author OmriAlfassi
	 */
	public String getHtmlString(){
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.getTitle()+"</h1>"+"<br>";

		for(int i=0; i<portSize;i++)
		{
			Stock tStock = (Stock) stocks[i];
			htmlResString = htmlResString + tStock.getHtmlDescription()+"<br>";
		}
		htmlResString += "Total Portfolio Value :"+this.getTotalValue()+ "$.<br>"+
				"Total Stocks Value :"+this.getStocksValue()+"$. <br>"+"Balance :"+this.getBalance()+"$.";
		return htmlResString;	
	}

	public void updateBalance(float amount)throws BalanceException{
		if ( amount < 0 ){
			float validate=amount+ this.balance;
			if ( validate < 0 ){
				throw new BalanceException("Balance is to low, canot execute.");
			}
			else{
				this.balance+=amount;
				System.out.println("youe cuurent balnce has been updated to: "+this.balance);
			}
		}
		else{
			this.balance+=amount;
			System.out.println("youe cuurent balnce has been updated to: "+this.balance);
		}	
	}
	/**
	 * return value of all stocks(portfolio value) 
	 * @return value
	 */
	public float getStocksValue(){
		float value=0;
		for (int i=0;i<this.portSize;i++){
			value += ((Stock) this.stocks[i]).getStockQuantity()*this.stocks[i].getBid();
		}
		return value;
	}
	/**
	 * return value of all stocks(portfolio value) + portfolio balance
	 * @return float
	 */

	public StockInterface findStock(String symbol) {
		int i = 0;
		for( i = 0; i< this.portSize; i++){
			if(symbol.equals(this.stocks[i].getSymbol())){
				return this.stocks[i];
			}
		}
		return null;
	}


	public float getTotalValue(){
		return this.balance+this.getStocksValue();
	}
	public float getBalance(){
		return this.balance;
	}
	public void setBalance(float value){
		this.balance=value;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public StockInterface[] getStocks() {
		return stocks;
	}

	public void setStock(Stock[] stock) {
		this.stocks = stocks;
	}
	public  int getPortfolioSize() {
		return portSize;
	}
	public static int getMaxSize() {
		return MAX_PORTFOLIO_SIZE;
	}

}
