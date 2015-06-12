package com.mta.javacourse.servlet;
import javax.servlet.ServletException;
import org.algo.service.ServiceManager;
import com.mta.javacourse.service.PortfolioManager;
import com.mta.javacourse.excepsion.PortfolioFullException;
import com.mta.javacourse.excepsion.StockAlreadyExistsException;
import com.mta.javacourse.excepsion.StockNotExistException;
import com.mta.javacourse.excepsion.BalanceException;

@SuppressWarnings("serial")
public class InitServlet extends javax.servlet.http.HttpServlet{

	@Override
	public void init() throws ServletException {
		super.init();
		PortfolioManager pm = new PortfolioManager();
		ServiceManager.setPortfolioManager(pm);
	}

}
