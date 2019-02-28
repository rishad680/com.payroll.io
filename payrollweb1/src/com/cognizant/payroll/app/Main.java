package com.cognizant.payroll.app;

import java.util.Scanner;

import com.cognizant.payroll.dao.LoginDao;
import com.cognizant.payroll.exception.PayrollException;
import com.cognizant.payroll.model.LoginDetails;

public class Main {

	public static void main(String[] args)throws PayrollException {

		Scanner scan = new Scanner(System.in);
		System.out.println("Enter user name : ");
		String username = scan.next();
		System.out.println("Enter password : ");
		String password = scan.next();

		LoginDao logindao = new LoginDao();
		String user="";
		try {
			user = logindao.doLogin(new LoginDetails(username, password));
		} catch (PayrollException e) {
			System.out.println(e.getMessage());
		}
		if (user.equals("")) {
			System.out.println("wrong username or password!!!");
		} 
		else {
			System.out.println("Successfully loged in " + user);
		}
		scan.close();

	}

}
