package com.mta.javacourse.model;



/**
 * Portfolio contains info about stocks
 * @author OmriAlfassi
 */

public class Portfolio {
	private String title;
	private final static int MAX_PORTFOLIO_SIZE = 5;
	private Stock[] stocks;
	private int portSize;
	private float balance;
	public enum ALGO_RECOMMENDATION {
		BUY, SELL, REMOVE, HOLD 
	}

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
		if(this.portSize<MAX_PORTFOLIO_SIZE && stock != null){
			for (int i=0;i<this.portSize;i++){
				if (this.stocks[i].getSymbol().equals(stock.getSymbol())){
					System.out.println("Stock allready exists in portfolio");
					return;
				}
			}
			stocks[this.portSize] = stock;
			stocks[this.portSize].setStockQuantity(0);
			portSize++;
			return;
		}
		else {
			if (stock == null){
				System.out.println("Stock is Invalide");
			}
			else{
				System.out.println("Can’t add new stock, portfolio can have only "+ MAX_PORTFOLIO_SIZE +"stocks");
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
	 * remove stock from portfolio by selling the stock and deleting it fom portfolio
	 * @return boolean
	 * @param symbol
	 */
	public boolean removeStock(String symbol){
		if (isStockInPort(symbol)>-1){
			int i=isStockInPort(symbol);
			if (i==-1){
				System.out.println("Stock does not found in protfolio");
				return false;
			}
			else{
				sellStock(symbol, -1);
				deleteStock(this.stocks[i]);
				return true;
			}
		}
		else{
			return false;
		}
	}
	/**
	 * sell specific stock by amount 
	 * @return boolean
	 * @param symbol, quantity
	 */
	public boolean sellStock (String symbol,int quantity){
		if (quantity<-1){
			System.out.println("Invalide amount of stocks to sell");
			return false;
		}
		else{
			if (isStockInPort(symbol)==-1){
				System.out.println("Stock is not in protfolio");
				return false;
			}
			else{
				int i=isStockInPort(symbol);
				if (this.stocks[i].getStockQuantity()<quantity){
					System.out.println("Not enough stocks to sell");
					return false;
				}
				else{
					if (quantity!= -1){
						updateBalance(quantity*this.stocks[i].getBid());
						int amount=this.stocks[i].getStockQuantity()-quantity;
						this.stocks[i].setStockQuantity(amount);
						this.stocks[i].setRecommendation(ALGO_RECOMMENDATION.SELL);
						return true;
					}
					else{
						updateBalance(this.stocks[i].getStockQuantity()*this.stocks[i].getBid());
						this.stocks[i].setStockQuantity(0);
						this.stocks[i].setRecommendation(ALGO_RECOMMENDATION.SELL);
						return true;
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
	public boolean buyStock (Stock stock, int quantity){
		if (stock == null){
			System.out.println("Stock is Invalide");
			return false;
		}
		else{
			if (stock.getAsk()*quantity>this.balance || this.balance<stock.getAsk()){
				System.out.println("“Not enough balance to complete purchase.");
				return false;
			}
			else{
				if (quantity<-1){
					System.out.println("Invalide amount of stocks to buy");
					return false;
				}
				else{
					if (isStockInPort(stock.getSymbol())==-1){
						if (this.portSize==MAX_PORTFOLIO_SIZE){
							System.out.println("Stock does not exists already in portfolio and there is no room to add it.");
							return false;
						}
						else{
							addStock(stock);
							int i=isStockInPort(stock.getSymbol());
							if (quantity == -1){
								int amount = (int) (this.balance/this.stocks[i].getAsk());	
								this.stocks[i].setStockQuantity(amount);
								this.balance -=amount*this.stocks[i].getAsk();
								return true;
							}
							else{
								this.stocks[i].setStockQuantity(quantity);
								this.balance -=quantity*this.stocks[i].getAsk();
								return true;
							}
						}
					}
					else{
						int i=isStockInPort(stock.getSymbol());
						if (quantity == -1){
							int amount = (int) (this.balance/this.stocks[i].getAsk());
							this.balance -=amount*this.stocks[i].getAsk();
							amount +=this.stocks[i].getStockQuantity();
							this.stocks[i].setStockQuantity(amount);
							return true;
						}
						else{
							this.balance -=this.stocks[i].getAsk()*quantity;
							int amount=this.stocks[i].getStockQuantity()+quantity;
							this.stocks[i].setStockQuantity(amount);
							return true;
						}
					}
				}
			}
		}
	}

	/**
	 * delete stocks with same symbol as asked to delete 
	 * @param stock
	 */
	public void deleteStock(Stock stock){
		for(int i = 0; i< this.portSize; i++){
			if((this.stocks[i].getSymbol().equals(stock.getSymbol()))){
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
	 * @return htmlResString
	 * @author OmriAlfassi
	 */
	public String getHtmlString(){
		String htmlResString = new String();
		htmlResString = htmlResString+"<h1>"+this.getTitle()+"</h1>"+"<br>";

		for(int i=0; i<portSize;i++)
		{
			Stock tStock = stocks[i];
			htmlResString = htmlResString + tStock.getHtmlDescription()+"<br>";
		}
		htmlResString += "Total Portfolio Value :"+this.getTotalValue()+ "$.<br>"+
		"Total Stocks Value :"+this.getStocksValue()+"$. <br>"+"Balance :"+this.getBalance()+"$.";
		return htmlResString;	
	}

	public void updateBalance(float amount){
		if ( amount < 0 ){
			float validate=amount+ this.balance;
			if ( validate < 0 ){
				System.out.println("The amount that you whish to withdraw is bigger than your balance");
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
			value += this.stocks[i].getStockQuantity()*this.stocks[i].getBid();
		}
		return value;
	}
	/**
	 * return value of all stocks(portfolio value) + portfolio balance
	 * @return float
	 */
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
