package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Question;
import model.Quiz;

public class QuizDAO {
	

	// Constructor to initialize the database connection

	// Method to get all quizzes from the database
	public List<Quiz> getAllQuizzes() {
		List<Quiz> quizList = new ArrayList<>();
		String query = "SELECT quiz_id, quiz_title FROM quiz"; // Adjust to your actual table structure

		try {
			Connection con = DBConnect.getCon();
			
			PreparedStatement ps = con.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				int quizId = rs.getInt("quiz_id");
				String quizTitle = rs.getString("quiz_title");

				Quiz quiz = new Quiz(quizId, quizTitle);
				quizList.add(quiz);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return quizList;
	}

	public boolean addQuiz(String quizTitle) {
		// SQL query to insert a new quiz with only quiz_title (quiz_id is
		// auto-generated)
		String query = "INSERT INTO quiz (quiz_title) VALUES (?)";

		try  {
			Connection con = DBConnect.getCon();
			PreparedStatement ps = con.prepareStatement(query);
			// Set the quiz title as the parameter for the query
			ps.setString(1, quizTitle);

			// Execute the query and check if the row was inserted
			int rowsAffected = ps.executeUpdate();
			
			return rowsAffected > 0 ; // Return true if the quiz was added successfully
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // Return false if there was an issue
	}
	
	 public int getQuizId(String quizTitle) {
	        String query = "SELECT quiz_id FROM quiz WHERE quiz_title = ?";
	        try{
	        	Connection con=DBConnect.getCon();
	        	PreparedStatement ps = con.prepareStatement(query);
	            ps.setString(1, quizTitle);
	            ResultSet rs = ps.executeQuery();
	            if (rs.next()) {
	                return rs.getInt("quiz_id");
	            }
	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	        return -1; // Return -1 if no quiz with the given title is found
	    }
	
	 public boolean addQuestionToQuiz(int quizId, Question question) {
		    boolean isAdded = false;
		    // Assuming you have a method to connect to your database (e.g., Connection conn)
		    try {
		        String query = "INSERT INTO questions (quiz_id, question_title, option1, option2, option3, option4, correct_answer) VALUES (?, ?, ?, ?, ?, ?, ?)";
		        Connection con=DBConnect.getCon();
		        PreparedStatement stmt = con.prepareStatement(query);
		        stmt.setInt(1, quizId);
		        stmt.setString(2, question.getQuestionTitle());
		        stmt.setString(3, question.getOption1());
		        stmt.setString(4, question.getOption2());
		        stmt.setString(5, question.getOption3());
		        stmt.setString(6, question.getOption4());
		        stmt.setInt(7, question.getCorrectAnswer());

		        int rowsAffected = stmt.executeUpdate();
		        if (rowsAffected > 0) {
		            isAdded = true;
		        }
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return isAdded;
		}
	 
	// Method to delete a quiz by quizId
	 public boolean deleteQuiz(int quizId) {
		    // Step 1: Delete child records first
		    String deleteQuestionsQuery = "DELETE FROM questions WHERE quiz_id = ?";
		    int rowCount=0;

		    try (Connection con = DBConnect.getCon();
		         PreparedStatement deleteQuestionsStmt = con.prepareStatement(deleteQuestionsQuery);
		         ) {

		        // Delete all questions related to the quiz
		        deleteQuestionsStmt.setInt(1, quizId);
		        deleteQuestionsStmt.executeUpdate();

		        // Step 2: Now delete the quiz
		        String deleteQuizQuery = "DELETE FROM quiz WHERE quiz_id = ?";
		        try (PreparedStatement deleteQuizStmt = con.prepareStatement(deleteQuizQuery)) {
		            deleteQuizStmt.setInt(1, quizId);
		            rowCount=deleteQuizStmt.executeUpdate();
		        }
		        
		    } catch (SQLException e) {
		        e.printStackTrace();
		    }
		    return rowCount>0;
		}


}
