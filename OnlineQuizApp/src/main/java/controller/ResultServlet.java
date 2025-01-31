package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.QuestionDAO;
import dao.ResultsDAO;
import model.Question;

@WebServlet("/result")
public class ResultServlet extends HttpServlet 
{
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// Retrieve the quiz ID and username (assuming it's stored in session)
	    int quizId = Integer.parseInt(request.getParameter("quizId"));
	    HttpSession session=request.getSession(false);
	    String username = (String) request.getParameter("username");
	    
	    // Fetch all questions for this quiz
	    List<Question> questions = new QuestionDAO().getQuestionsForQuiz(quizId);

	    // Initialize variables to calculate the score
	    int totalQuestions = questions.size();
	    int correctAnswers = 0;

	    // Loop through each question and compare the submitted answer
	    Map<Integer, Integer> submittedAnswers = new HashMap<>();
	    for (Question question : questions) {
	        int questionId = question.getQuestionId();

	        // Retrieve the user's answer for this question
	        String answerParam = request.getParameter("question_" + questionId);
	        if (answerParam != null && !answerParam.isEmpty()) {
	            int userAnswer = Integer.parseInt(answerParam);
	            submittedAnswers.put(questionId, userAnswer);

	            // Check if the user's answer matches the correct answer
	            if (userAnswer == question.getCorrectAnswer()) { // Updated method call
	                correctAnswers++;
	            }
	        }
	    }

	    // Calculate the score percentage
	    double scorePercentage = ((double) correctAnswers / totalQuestions) * 100;
	    
	    ResultsDAO dao=new ResultsDAO();
	    int rowCount=dao.insertData(username, quizId, correctAnswers, scorePercentage);
	    
	    if(rowCount>0)
	    {
	    	request.setAttribute("map", submittedAnswers);
	    	request.setAttribute("questions", questions);
	    	request.setAttribute("totalQuestions", totalQuestions);
	    	request.setAttribute("correctAnswers", correctAnswers);
	    	request.setAttribute("scorePercentage", scorePercentage);
	    	RequestDispatcher rd=request.getRequestDispatcher("submit.jsp");
	    	rd.forward(request, response);
	    }
	    else
	    {
	    	request.setAttribute("msg", "Something Went Wrong");
	    	RequestDispatcher rd=request.getRequestDispatcher("quiz.jsp");
	    	rd.forward(request, response);
	    }
	}
}
