package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.User;

public class UserDAO {
    
    // Method to register a new user
    public boolean registerUser(User user) {
        boolean isRegistered = false;
        String query = "INSERT INTO users VALUES (?, ?, ?, ?)";

        try {
        		Connection conn = DBConnect.getCon();
        
             PreparedStatement stmt = conn.prepareStatement(query);
            
            // Check if username or email already exists
            if (userExists(user.getUsername(),user.getPassword()) != null) {
                return false; // User already exists
            }

            // Set parameters for the prepared statement
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getFullName());
           

            // Execute the query
            int rowsAffected = stmt.executeUpdate();
            if (rowsAffected > 0) {
                isRegistered = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isRegistered;
    }

    // Method to check if a user already exists by username or email
    public User userExists(String username,String password) {
    	
    	User user=null;
    	
        String checkQuery = "SELECT * FROM users WHERE username = ? OR email = ?";
        try (Connection conn = DBConnect.getCon();
             PreparedStatement stmt = conn.prepareStatement(checkQuery)) {
            
            stmt.setString(1, username);
            stmt.setString(2, password);
            
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
               
            	user=new User();
            	
            	user.setUsername(rs.getString(1));
            	user.setPassword(rs.getString(2));
            	user.setEmail(rs.getString(3));
            	user.setFullName(rs.getString(4));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }
}
