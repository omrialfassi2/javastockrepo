package com.mta.javacourse;

public class stcok {
	private String symbol;
	private Float ask;
	private Float bid;
	private java.util.Date date;
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
		
		“<b>Stock symbol</b>: “+getSymbol()+” <b>Bid</b>: “+getBid()
	}
}