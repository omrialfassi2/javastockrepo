package com.mta.javacourse;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class mathServlet extends HttpServlet{
	
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		resp.setContentType("text/html");

				double circleArea;
				int radius = 50;
				circleArea = Math.PI*radius*radius;
				String circle = new String("Calc 1: Area of circle with radius: "+radius+" is "+circleArea+" square cm.");

				
				double degree = 30;
				double hypotenus = 50;
				double sinus = java.lang.Math.sin(java.lang.Math.toRadians(degree));
				double opposite =sinus*hypotenus;
				
				String oppositeResult = new String("Calc 2: Length of opposite where angle B is: "+degree+" degrees, "
						+ "and the Hypotenuse "	+ "length is "+hypotenus+" is: "+opposite+" cm.");
				
							
				
				double powResult, base = 20, exp = 13;
				powResult = java.lang.Math.pow(base, exp);
				String powerResult = new String("Calc 3: Power of "+base+" with exp of "+exp+" is "+powResult+".");
				
				String finalResult = circle+"<br><br>"+oppositeResult+"<br><br>"+powerResult;
				resp.getWriter().println(finalResult);
		
}
}
