package com.cognizant.payroll.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.cognizant.payroll.exception.IPayrollMessages;
import com.cognizant.payroll.exception.PayrollException;
import com.cognizant.payroll.model.LoginDetails;
import com.cognizant.payroll.util.ConnectionUtil;

public class LoginDao {

	public String doLogin(LoginDetails loginDetails) throws PayrollException {

		String user = "";
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet result = null;
		try {
			con = ConnectionUtil.getConnection();
			pstmt = con.prepareStatement("select username,password from login where username=? and password=?");
			pstmt.setString(1, loginDetails.getUserName());
			pstmt.setString(2, loginDetails.getPassword());
			result = pstmt.executeQuery();
			if (result.next()) {
				user = result.getString(1);
			}
		} catch (ClassNotFoundException classNotFoundException) {
			throw new PayrollException(IPayrollMessages.DRIVER);

		}catch (SQLException sqlException) {
			throw new PayrollException(IPayrollMessages.SQL);

		}catch (Exception exception) {
			throw new PayrollException(IPayrollMessages.CONTACT);
		}

		finally {

			try {
				if (result != null)
					result.close();
				
				if (pstmt != null)
					pstmt.close();
				
				if (con != null)
					con.close();
				
			} catch (SQLException e) {
				throw new PayrollException(e.getMessage());
			}

		}

		return user;
	}

}
