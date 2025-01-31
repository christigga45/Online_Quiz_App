package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Admin;

public class AdminDAO {
	

	// Constructor: Initialize the connection to the database

	// Method to validate admin credentials (username and password)
	public Admin validateAdmin(String username, String password) {
		Admin bean=null;
        String query = "SELECT * FROM admins WHERE username = ? AND password = ?";
        
        try {
        	Connection con=DBConnect.getCon();
        	PreparedStatement ps = con.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            
            ResultSet rs = ps.executeQuery();
            
            if (rs.next()) {
            	
            	bean=new Admin();
            	
            	bean.setAdmin_id(rs.getString(1));
            	bean.setUsername(rs.getString(2));
            	bean.setPassword(rs.getString(3));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return bean;  // Invalid admin credentials
    }
}
