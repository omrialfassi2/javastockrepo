package com.mta.javacourse.excepsion;
import org.algo.exception.PortfolioException;

public class BalanceException extends PortfolioException{

	public BalanceException(){
		super("There is not enough BALANCE in Portfolio");
	}
	
	public BalanceException(String errorString){
		super(errorString);
	}
}
 	