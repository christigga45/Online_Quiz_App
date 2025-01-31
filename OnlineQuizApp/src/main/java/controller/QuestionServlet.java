package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.QuestionDAO;
import model.Question;

@WebServlet("/quiz")
public class QuestionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve the quiz ID from the request
        String quizIdString = request.getParameter("quizId");

        if (quizIdString == null || quizIdString.isEmpty()) {
            // Redirect to error page if quiz ID is invalid
            request.setAttribute("errorMessage", "Quiz ID is missing or invalid.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        int quizId = Integer.parseInt(quizIdString);

        // Fetch questions for the quiz using the DAO
        QuestionDAO questionDAO = new QuestionDAO();
        List<Question> questions = questionDAO.getQuestionsForQuiz(quizId);

        if (questions.isEmpty()) {
            // Redirect to error page if no questions are found
            request.setAttribute("errorMessage", "No questions found for this quiz.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Set attributes and forward to the quiz JSP
        request.setAttribute("quizId", quizId);
        request.setAttribute("questions", questions);
        request.setAttribute("quizTitle", "Sample Quiz Title"); // Example: fetch dynamically if needed
        request.getRequestDispatcher("quiz.jsp").forward(request, response);
    }
}
