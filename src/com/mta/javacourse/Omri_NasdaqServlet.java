package com.mta.javacourse;

import java.io.IOException;
import javax.servlet.http.*;

@SuppressWarnings("serial")
public class Omri_NasdaqServlet extends HttpServlet {
	public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/html");
		int num1=4;
		int num2=3;
		int num3=7;
		int result=(num1+num2)*num3;
		String resultStr=new String("<h1>result of"+num1+"+"+num2+"*"+num3+"="+result+"</h1>");
		resp.getWriter().println(resultStr);
	}

}
