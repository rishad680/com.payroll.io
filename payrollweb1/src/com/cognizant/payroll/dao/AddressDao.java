package com.cognizant.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.cognizant.payroll.exception.PayrollException;
import com.cognizant.payroll.model.Address;

public class AddressDao {

	public AddressDao() {
		// TODO Auto-generated constructor stub
	}
	public int saveAddress(Address address,Connection con) throws PayrollException
	{
		
		//Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet resultSet = null;
		int addressId=0;
		String query="insert into address(AD_STREET,AD_CITY,AD_STATE,AD_COUNTRY,AD_PIN) values(?,?,?,?,?)";
		try {
			//con=ConnectionUtil.getConnection();
			pstmt=con.prepareStatement(query,Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, address.getStreet());
			pstmt.setString(2, address.getCity());
			pstmt.setString(3, address.getState());
			pstmt.setString(4, address.getCountry());
			pstmt.setInt(5, address.getPin());
			
			int count=pstmt.executeUpdate();
			resultSet=pstmt.getGeneratedKeys();
			if(resultSet.next())
			{
				addressId=resultSet.getInt(1);
			}
			if(count>=1)
				System.out.println("addressis saved");
			else
				throw new PayrollException("Address not saved");
			
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
		return addressId;
		
	}
}
