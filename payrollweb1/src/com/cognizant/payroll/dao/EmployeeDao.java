package com.cognizant.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognizant.payroll.exception.PayrollException;
import com.cognizant.payroll.model.Employee;


public class EmployeeDao {

	public EmployeeDao() {
		// TODO Auto-generated constructor stub
	}
	public int saveEmployee(Employee employee,Connection con) throws PayrollException
	{
		
		//Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int empId=0;
		String query="insert into employee(EM_NAME,EM_DOB,EM_MOBILE,EM_SALARY,EM_EMAIL,EM_DP_FK,EM_AD_FK,EM_DE_FK,gender) values(?,?,?,?,?,?,?,?,?)";
		try {
			//con=ConnectionUtil.getConnection();
			pstmt=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, employee.getEmployeeName());
			pstmt.setString(2, employee.getDob().toString());
			pstmt.setLong(3, employee.getMobile());
			pstmt.setDouble(4, employee.getSalary());
			pstmt.setString(5, employee.getEmail());
			pstmt.setInt(6,employee.getDepartment().getDepartmentId() );
			pstmt.setInt(7, employee.getAddress().getAddressId());
			pstmt.setInt(8, employee.getDesignation().getDesignationId());
			pstmt.setString(9, employee.getGender()+"");
			
			int count=pstmt.executeUpdate();
			resultSet=pstmt.getGeneratedKeys();
			if(resultSet.next())
			{
				empId=resultSet.getInt(1);
			}
			if(count>=1)
				System.out.println("employee registered");
			else
				System.out.println("employee not registered");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				if(pstmt!=null)
					pstmt.close();
				if(resultSet!=null)
					resultSet.close();
				
			}
			catch(SQLException e)
			{
				throw new PayrollException(e.getMessage());
			}
		}
		return empId;
}
}
