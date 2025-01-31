package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Result;

public class ResultsDAO 
{
	
	public List<Result> getAllResults(String quizTitle) {
	    List<Result> results = new ArrayList<>();
	    String query = "SELECT u.username, r.score, r.percentage " +
	                   "FROM users u " +
	                   "JOIN results r ON u.username = r.username " +
	                   "JOIN quiz q ON r.quiz_id = q.quiz_id " +
	                   "WHERE q.quiz_title = ? " + // Replace with the desired quiz title
	                   "ORDER BY r.percentage DESC, r.score DESC";

	    try {
	    	Connection con = DBConnect.getCon();
	    	PreparedStatement ps = con.prepareStatement(query);
	    	ps.setString(1, quizTitle);
	    	 ResultSet rs = ps.executeQuery();

	        while (rs.next()) {
	            String username = rs.getString("username");
	            int score = rs.getInt("score");
	            double percentage = rs.getDouble("percentage");

	            Result result = new Result(username, score, percentage);
	            results.add(result);
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return results;
	}

		
	public int insertData(String username,int quizId,int correctAnswers,double scorePercentage)
	   {
			int rowsInserted=0;
		   String insertQuery = "INSERT INTO results (username, quiz_id, score, percentage) VALUES (?, ?, ?, ?)";
		    try {
		        // Database connection details
		       Connection con=DBConnect.getCon();
		       PreparedStatement ps=con.prepareStatement(insertQuery);

		        // Insert query
		        ps.setString(1, username);
		        ps.setInt(2, quizId);
		        ps.setInt(3, correctAnswers);
		        ps.setDouble(4, scorePercentage);

		        rowsInserted = ps.executeUpdate();
		        
		        
		    } catch (Exception e) {
		        e.printStackTrace();
		        
		    }
		    return rowsInserted;
	   }

}
