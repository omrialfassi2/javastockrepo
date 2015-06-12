package com.mta.javacourse.excepsion;
import org.algo.exception.PortfolioException;


public class StockNotExistException extends PortfolioException{

	public StockNotExistException(){
		super("Stock Was not found in portfolio");
	}
	
	public StockNotExistException(String stock){
		super("Stock " + stock +  " Was not found in portfolio");
	}
}
