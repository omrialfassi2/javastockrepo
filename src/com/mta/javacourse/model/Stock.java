package com.mta.javacourse.model;

import java.text.*;
import java.util.*;

/**
 * Stock contains details about individual stock
 * @author OmriAlfassi
 */
public class Stock {
	private String symbol;
	private Float ask;
	private Float bid;
	private java.util.Date date;
	private SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");
	private int recommendation,stockQuantity;
	private static final int BUY = 0;
	private static final int SELL = 1;
	private static final int REMOVE = 2;
	private static final int HOLD = 3;
	
   public Stock (String nSymbol, float nBid, float nAsk, Date nDate ){
	   this.symbol=nSymbol;
	   this.bid=nBid;
	   this.ask=nAsk;
	   this.date=nDate;
	   this.recommendation=0;
	   this.stockQuantity=0;
   }
	
   /**
	 * Copy C'tor of Stock class.
	 * @param originalStock
	 * @author OmriAlfassi
	 */
   public Stock (Stock originalStock)
	{
		this(originalStock.getSymbol(),originalStock.getBid(),originalStock.getAsk(),originalStock.getDate());
		this.recommendation = originalStock.getRecommendation();
		this.stockQuantity = originalStock.getStockQuantity();
	}
	
	public int getBUY() {
		return BUY;
	}
	public int getSELL() {
		return SELL;
	}
	public int getREMOVE() {
		return REMOVE;
	}
	public int getHOLD() {
		return HOLD;
	}
	public int getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(int recommendation) {
		this.recommendation = recommendation;
	}
	public int getStockQuantity() {
		return stockQuantity;
	}
	public void setStockQuantity(int stockQuantity) {
		this.stockQuantity = stockQuantity;
	}
	public String getSymbol() {
		return symbol;
	}
	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}
	public Float getAsk() {
		return ask;
	}
	public void setAsk(Float ask) {
		this.ask = ask;
	}
	public Float getBid() {
		return bid;
	}
	public void setBid(Float bid) {
		this.bid = bid;
	}
	public Date getDate() {
		return this.date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public String getHtmlDescription(){
		
		return "<b>Stock symbol </b>: "+this.getSymbol()+"<b> Bid</b>: "+this.getBid()+"<b> Ask</b>: "+this.getAsk()+"<b> date </b>:" +formDate.format(this.getDate())+"</br>";
	}
	public SimpleDateFormat getFormDate() {
		return formDate;
	}

	public void setFormDate(SimpleDateFormat formDate) {
		this.formDate = formDate;
	}
	
}