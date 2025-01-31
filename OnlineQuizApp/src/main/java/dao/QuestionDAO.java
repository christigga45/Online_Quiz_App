package dao;

import model.Question;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class QuestionDAO {

    // Method to fetch questions for a specific quiz
    public List<Question> getQuestionsForQuiz(int quizId) {
        List<Question> questions = new ArrayList<>();
        String query = "SELECT * FROM questions WHERE quiz_id = ?"; // Adjust table and column names as necessary

        try {
        	Connection conn = DBConnect.getCon();
        	PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, quizId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                // Create new Question object for each record in the result set
                int questionId = rs.getInt("question_id");
                String questionTitle = rs.getString("question_title");
                String option1 = rs.getString("option1");
                String option2 = rs.getString("option2");
                String option3 = rs.getString("option3");
                String option4 = rs.getString("option4");
                int correctAnswer = rs.getInt("correct_answer");

                Question question = new Question(questionId, quizId, questionTitle, option1, option2, option3, option4, correctAnswer);
                questions.add(question);
            }

        } catch (SQLException e) {
            e.printStackTrace(); // Handle exceptions properly in production code
        }

        return questions;
    }
}
