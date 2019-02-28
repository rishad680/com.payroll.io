package com.cognizant.payroll.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cognizant.payroll.dao.LoginDao;
import com.cognizant.payroll.exception.PayrollException;
import com.cognizant.payroll.model.LoginDetails;
import com.sun.corba.se.spi.protocol.RequestDispatcherDefault;

/**
 * Servlet implementation class LoginController
 */
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Servlet#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	
		System.out.println("post service");
		
		ServletConfig config=this.getServletConfig();
		PrintWriter out=response.getWriter();
		if(request.getParameter("submit").equals("Submit")) {
			String username=request.getParameter("username");
			String password=request.getParameter("pass");
			
			LoginDetails logindetails=new LoginDetails(username,password);
			LoginDao loginDao=new LoginDao();
			try {
				String name=loginDao.doLogin(logindetails);
				if(name.equals("")) {
					//out.write("username and password is wrong");
					RequestDispatcher dispatcher=request.getRequestDispatcher("/html/login1.html");
					dispatcher.forward(request, response);
				}
				else {
					HttpSession httpSession=request.getSession();
					httpSession.setAttribute("user", logindetails);
					System.out.println(httpSession);
					//out.write("<p> user is valid </p>");
					RequestDispatcher dispatcher=request.getRequestDispatcher("/html/home.html");
					dispatcher.include(request, response);
					}} catch (PayrollException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
	}
	
	}

}
