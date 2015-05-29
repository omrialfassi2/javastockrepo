package com.mta.javacourse.model;

import java.text.*;
import java.util.*;
import org.algo.model.StockInterface;
import com.mta.javacourse.model.Portfolio.ALGO_RECOMMENDATION;

/**
 * Stock contains details about individual stock
 * @author OmriAlfassi
 */
public class Stock implements StockInterface {
	private String symbol;
	private float ask;
	private float bid;
	private java.util.Date date;
	transient private SimpleDateFormat formDate = new SimpleDateFormat("dd/MM/yyyy");
	private int stockQuantity;
	private ALGO_RECOMMENDATION recommendation;

	public Stock (){
		this.symbol = new String();
		this.bid = 0;
		this.ask = 0;
		this.date = new Date();
		this.recommendation = ALGO_RECOMMENDATION.HOLD;
		this.stockQuantity = 0;				
	}

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
	//	public void setRecommendation(ALGO_RECOMMENDATION recommendation) {
	//	this.recommendation = recommendation;
	//	}
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
	public float getAsk() {
		return ask;
	}
	public void setAsk(Float ask) {
		this.ask = ask;
	}
	public float getBid() {
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

	public void setRecommendation(ALGO_RECOMMENDATION typeOf) {
		this.recommendation = typeOf;
	}
}
