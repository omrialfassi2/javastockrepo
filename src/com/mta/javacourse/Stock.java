package com.mta.javacourse;

public class Stock {
	private String symbol;
	private Float ask;
	private Float bid;
	private java.util.Date date;
	
   public Stock (String nSymbol, float nBid, float nAsk){
	   this.symbol=nSymbol;
	   this.bid=nBid;
	   this.ask=nAsk;
	   this.date=new java.util.Date();
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
	public java.util.Date getDate() {
		return date;
	}
	public void setDate(java.util.Date date) {
		this.date = date;
	}
	public String getHtmlDescription(String name){
		
		return "<b>Stock symbol</b>: "+this.symbol+"<b>Bid</b>: "+this.bid+"<b> Ask</b>: "+this.ask+"<b> date </b>: "+formDate.format(this.date)""
	}
}