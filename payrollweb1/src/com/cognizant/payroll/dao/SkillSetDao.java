package com.cognizant.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognizant.payroll.exception.PayrollException;

import com.cognizant.payroll.model.EmployeeSkillSet;


public class SkillSetDao {

	public SkillSetDao() {
		// TODO Auto-generated constructor stub
	}
	public int saveSKillSet(EmployeeSkillSet employeeSkillSet,Connection con) throws PayrollException
	{
		//String user="";
		//Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int skillId=0;
		String query="insert into employee_skillset(EM_SS_FK,SK_SS_FK) values(?,?)";
		try {
			//con=ConnectionUtil.getConnection();
			pstmt=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setInt(1,employeeSkillSet.getEmployee().getEmployeeId());
			pstmt.setInt(2,employeeSkillSet.getSkill().getSkillId());
			int count=pstmt.executeUpdate();
			resultSet=pstmt.getGeneratedKeys();
			if(resultSet.next())
			{
				skillId=resultSet.getInt(1);
			}
			if(count>=1)
				System.out.println("skill saved");
			else
				throw new PayrollException("skill not saved");
			
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
		
		return skillId;
}
}
