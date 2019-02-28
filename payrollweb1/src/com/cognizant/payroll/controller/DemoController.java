package com.cognizant.payroll.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class DemoController
 */
public class DemoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DemoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		PrintWriter out=response.getWriter();
		response.setContentType("text/html");
		ServletConfig config=this.getServletConfig();
		
		ServletContext application=this.getServletContext();
		
		String location=application.getInitParameter("report location");
		out.print("the data from application object from xml file as"+location+"<br>");
	String fileinfo=config.getInitParameter("fileinfo");
	String dbdriver=config.getInitParameter("dbdriver");
	String dbpwd=config.getInitParameter("dbpwd");
	String dburl=config.getInitParameter("dburl");
	String dbuser=config.getInitParameter("dbuser");
	out.print("accessing the democontroller config object<br>");
	out.print(dbdriver+"<br>");
	out.print(dbpwd+"<br>");
	out.print(dburl+"<br>");
	out.print(dbuser+"<br>");
	out.print(fileinfo+"<br>");
	
	Enumeration<String> allParam=config.getInitParameterNames();
	while(allParam.hasMoreElements())
	{
		String string=(String) allParam.nextElement();
		System.out.println(string);
		System.out.println(config.getInitParameter(string));
	}
	}


}
