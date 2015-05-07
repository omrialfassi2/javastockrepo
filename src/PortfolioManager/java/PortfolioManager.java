package PortfolioManager.java;

import java.util.Calendar;
import java.util.Date;

import com.mta.javacourse.Stock;
import com.mta.javacourse.model.Portfolio;

public class PortfolioManager {
	public Portfolio getPortfolio(){
		Portfolio portfolio = new Portfolio("Portfolio");
		portfolio.setTitle("Portfolio");
		
		Calendar stockD = Calendar.getInstance();
		stockD.set(2014, 10, 15);
		
		Date stock1D=stockD.getTime();
		Date stock2D=stockD.getTime();
		Date stock3D=stockD.getTime();
		
		Stock stock1=new Stock("PIH",12.4F,13.1F,stock1D);
		Stock stock2=new Stock("AAL",5.5F,5.78F,stock2D);
		Stock stock3=new Stock("CAAS",31.5F,32.2F,stock3D);
		
		portfolio.addStock(stock1);
		portfolio.addStock(stock2);
		portfolio.addStock(stock3);
		
		return portfolio;
	}
}