package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class GetGeneratedId {
	public static void main(String[] args) throws SQLException {
		String sql = null;
		int result = 0;
		ResultSet rs = null;
		int num = 0;
		
	    try (Connection con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ideal", "root", "root")) {
            sql = "INSERT INTO testcase VALUES (DEFAULT,'ito'),Statement.RETURN_GENERATED_KEYS";
	        try (PreparedStatement pst = con.prepareStatement(sql)) {
	            result = pst.executeUpdate(,Statement.RETURN_GENERATED_KEYS);
	            System.out.println("res:" + result);
	        	rs = pst.getGeneratedKeys();
	        	
	        	System.out.println("set:" + rs);
	        	if(rs.next()) {
	        		num = rs.getInt(1);
	        		System.out.println("num:" + num);
	        	}
	        }

	    }
		
	}

}
