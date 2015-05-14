package com.mta.javacourse.service;

import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.model.Portfolio;
import com.mta.javacourse.model.Stock;
@SuppressWarnings("serial")
public class PortfolioManager {
	public Portfolio getPortfolio(){
		Portfolio portfolio = new Portfolio("Exercise 7 portfolio");
		portfolio.setBalance(10000);
		
		Calendar stockD = Calendar.getInstance();
		stockD.set(2014, 11, 15);
		
		Date stock1D=stockD.getTime();
		Date stock2D=stockD.getTime();
		Date stock3D=stockD.getTime();
		
		Stock stock1=new Stock("PIH",8.5F,10F,stock1D);
		Stock stock2=new Stock("AAL",25.5F,30F,stock2D);
		Stock stock3=new Stock("CAAS",15.5F,20F,stock3D);
		
		portfolio.buyStock(stock1, 20);
		portfolio.buyStock(stock2, 30);
		portfolio.buyStock(stock3, 40);
		
		portfolio.sellStock("AAL", -1);
		portfolio.removeStock("CAAS");
		
		return portfolio;
	}
}