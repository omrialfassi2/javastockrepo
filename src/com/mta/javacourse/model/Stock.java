package com.mta.javacourse.model;

import java.text.*;
import java.util.*;

import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

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
	private int stockQuantity;
	private ALGO_RECOMMENDATION recommendation;


	public Stock (String nSymbol, float nBid, float nAsk, Date nDate ){
		this.symbol=nSymbol;
		this.bid=nBid;
		this.ask=nAsk;
		this.date=nDate;
		this.recommendation=ALGO_RECOMMENDATION.HOLD;
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


	public ALGO_RECOMMENDATION getRecommendation() {
		return recommendation;
	}
	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
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

		return "<b>Stock symbol </b>: "+this.getSymbol()+
		"<b> Bid</b>: "+this.getBid()+"<b> Ask</b>: "+this.getAsk()+"<b> date </b>:" +
		formDate.format(this.getDate())+"<b> Quantity </b>:" +this.getStockQuantity();
	}
	public SimpleDateFormat getFormDate() {
		return formDate;
	}

	public void setFormDate(SimpleDateFormat formDate) {
		this.formDate = formDate;
	}
}
