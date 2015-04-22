package com.mta.javacourse;

import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@SuppressWarnings("serial")
public class StockDetailsServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");

		Calendar stockD = Calendar.getInstance();
		stockD.set(2014, 10, 15);
		
		Date stock1D=stockD.getTime();
		Date stock2D=stockD.getTime();
		Date stock3D=stockD.getTime();
		
		Stock stock1=new Stock("PIH",12.4F,13.1F,stock1D);
		Stock stock2=new Stock("AAL",5.5F,5.78F,stock2D);
		Stock stock3=new Stock("CAAS",31.5F,32.2F,stock3D);
		resp.getWriter().println(stock1.getHtmlDescription());
		resp.getWriter().println(stock2.getHtmlDescription());
		resp.getWriter().println(stock3.getHtmlDescription());


}
}
