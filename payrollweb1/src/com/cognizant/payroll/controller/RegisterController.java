package com.cognizant.payroll.controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import com.cognizant.payroll.dao.*;
import com.cognizant.payroll.exception.PayrollException;
import com.cognizant.payroll.model.*;
import com.cognizant.payroll.util.ConnectionUtil;


public class RegisterController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterController() {
        super();
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(request.getParameter("submit").equals("Register")) {
			
		String Name=request.getParameter("name");
		
		String dob=request.getParameter("dob").toString();
		LocalDate date=LocalDate.parse(dob);
		
		String mobile=request.getParameter("mb_num");
		long cell=Long.parseLong(mobile);
		
		String salary=request.getParameter("sal");
		double sal=Double.parseDouble(salary);
		
		String email=request.getParameter("email");
		String street=request.getParameter("street");
		String city=request.getParameter("city");
		String state=request.getParameter("state");
		
		String pin=request.getParameter("code");
		int pn=Integer.parseInt(pin);
		
		String country=request.getParameter("country");
		
		String gender=request.getParameter("gender");
		char gen=gender.charAt(0);
		
		String designationName=request.getParameter("DesignationId");
		int desigId=Integer.parseInt(designationName);
		
		String dpid=request.getParameter("departmentId");
		int did=Integer.parseInt(dpid);
		
		System.out.println(designationName);
		Connection con=null;
		try {
			con=ConnectionUtil.getConnection();
			if(con==null)
				System.out.println("not connected");
			else
				System.out.println("connected");
			
			con.setAutoCommit(false);
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {
			con.setAutoCommit(false);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		Address address=new Address(0, street, city, state,pn,country);
		AddressDao addressDao=new AddressDao();
		int generateId;
		try {
			generateId=addressDao.saveAddress(address,con);
			address.setAddressId(generateId);
		} catch (PayrollException e) {
			e.printStackTrace();
		}
		
		
		Department department=new Department();
		department.setDepartmentId(did);
		
		Designation designation=new Designation();
		designation.setDesignationId(desigId);
		
		Employee employee=new Employee(0, Name, date, cell, sal, email, gen, department, address,designation);
		EmployeeDao employeeDao=new EmployeeDao();
		try {
			int genEmpId=employeeDao.saveEmployee(employee, con);
			employee.setEmployeeId(genEmpId);
		} catch (PayrollException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		SkillSetDao skillSetDao=new SkillSetDao();
		String[] arr = request.getParameterValues("skillId");
		for (int i = 0; i < arr.length; i++) {
			String skillIdd=arr[i];
			int skillId=Integer.parseInt(skillIdd);
			EmployeeSkillSet employeeSkillSet=new EmployeeSkillSet();	
			Skill skill=new Skill();
			skill.setSkillId(skillId);
			employeeSkillSet.setEmployee(employee);
			employeeSkillSet.setSkill(skill);
			try {
				skillSetDao.saveSKillSet(employeeSkillSet, con);
			} catch (PayrollException e) {
				e.printStackTrace();
			}
		}
		try {
			con.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		}
	}

}
