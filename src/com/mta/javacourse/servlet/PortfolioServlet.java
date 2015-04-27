package com.mta.javacourse.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import PortfolioManager.java.PortfolioManager;

import com.mta.javacourse.model.Portfolio;


@SuppressWarnings("serial")
public class PortfolioServlet extends HttpServlet{

	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

	
	resp.setContentType("text/html");
	PortfolioManager portfolioManager= new PortfolioManager();
	Portfolio portfolio = portfolioManager.getPortfolio();
	resp.getWriter().println(portfolio.getHtmlString());
	}
}
