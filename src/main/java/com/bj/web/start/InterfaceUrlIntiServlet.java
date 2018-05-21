package com.bj.web.start;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

public class InterfaceUrlIntiServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;
	 
    @Override
    public void init(ServletConfig config) throws ServletException {
        InterfaceUrlInti.init();
    }
}
