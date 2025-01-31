package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuizDAO;
import model.Quiz;

@WebServlet("/quizListServlet")
public class QuizListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create QuizDAO instance to fetch quizzes from the database
        QuizDAO quizDAO = new QuizDAO();
        
        // Fetch the list of quizzes
        List<Quiz> quizList = quizDAO.getAllQuizzes();
        
        // Set the quiz list in the request attribute
        request.setAttribute("quizList", quizList);
        
        // Forward the request to the quizList.jsp page
        request.getRequestDispatcher("quizList.jsp").forward(request, response);
    }
}
